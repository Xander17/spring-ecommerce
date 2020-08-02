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
import ru.geekbrains.adminui.dto.csv.ProductCsv;
import ru.geekbrains.adminui.mapper.ProductMapper;
import ru.geekbrains.shopdb.model.Product;

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

    private final ProductMapper productMapper;

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

    private GenericTransformer<File, List<Product>> transformer() {
        return source -> {
            List<ProductCsv> products;
            try {
                log.info("Start read file '{}'", source.getName());
                products = CsvProductMapper.parse(source, ProductCsv.class);
            } catch (Exception e) {
                products = Collections.emptyList();
            }
            source.delete();

            return products.stream()
                    .map(productMapper::fromCsvToEntity)
                    .collect(Collectors.toList());
        };
    }
}
