package ru.geekbrains.adminui.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaUpdatingOutboundEndpointSpec;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageHandler;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;
import ru.geekbrains.adminui.mapper.CsvImportMapper;
import ru.geekbrains.adminui.mapper.MapperFactory;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ImportConfig {

    @Value("${app.import.csv-product-dir}")
    private String srcDirectory;

    @Value("${app.import.files-check-rate}")
    private int filesCheckRate;

    private final MapperFactory mapperFactory;

    @Bean
    public IntegrationFlow integrationFlow(MessageSource<File> source, @Qualifier("insertHandler") MessageHandler handler) {
        return IntegrationFlows.from(source, spec -> spec.poller(Pollers.fixedRate(filesCheckRate)))
                .transform(transformer())
                .handle(handler, ConsumerEndpointSpec::transactional)
                .get();
    }

    @Bean
    public MessageSource<File> source() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(srcDirectory));
        source.setFilter(this::filterFiles);
        source.setAutoCreateDirectory(true);
        return source;
    }

    private List<File> filterFiles(File[] files) {
        return Arrays.stream(files)
                .filter(file -> file.getName().endsWith(".csv"))
                .collect(Collectors.toList());
    }

    @Bean
    public JpaUpdatingOutboundEndpointSpec insertHandler(EntityManagerFactory em) {
        return Jpa.outboundAdapter(em)
                .persistMode(PersistMode.PERSIST);
    }

    private GenericTransformer<File, List<?>> transformer() {
        return new GenericTransformer<File, List<?>>() {
            @Override
            public List<?> transform(File source) {
                String prefix = source.getName().split("_")[0];
                CsvImportType type = CsvImportType.getTypeByPrefix(prefix);
                if (type == CsvImportType.NONE) {
                    return Collections.emptyList();
                }
                return genericTransform(source, type);
            }

            private List<?> genericTransform(File sourse, CsvImportType type) {
                return genericTransform(sourse, type, type.getDtoClass(), type.getEntityClass());
            }

            private <S, D> List<D> genericTransform(File source, CsvImportType type, Class<S> srcClass, Class<D> dstClass) {
                List<S> srcList;
                try {
                    log.info("Start read file '{}'", source.getName());
                    srcList = CsvProductMapper.parse(source, srcClass);
                } catch (Exception e) {
                    srcList = Collections.emptyList();
                }
                source.delete();

                CsvImportMapper<S, D> mapper = mapperFactory.getMapper(type, srcClass, dstClass);
                return srcList.stream()
                        .map(mapper::fromCsvToEntity)
                        .collect(Collectors.toList());
            }
        };
    }
}
