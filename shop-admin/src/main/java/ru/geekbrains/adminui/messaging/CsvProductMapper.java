package ru.geekbrains.adminui.messaging;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class CsvProductMapper {

    @SneakyThrows
    public static <T> List<T> parse(File file, Class<T> parseClass) {
        CsvToBean<T> csv = new CsvToBeanBuilder<T>(Files.newBufferedReader(file.toPath()))
                .withType(parseClass)
                .build();
        return csv.parse();
    }
}
