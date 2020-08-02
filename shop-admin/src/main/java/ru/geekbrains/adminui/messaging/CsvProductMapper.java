package ru.geekbrains.adminui.messaging;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class CsvProductMapper {

    @SneakyThrows
    public static <T> List<T> parse(File file, Class<T> parseClass) {
        CsvToBean<T> csv = new CsvToBean<>();
        CSVReader csvReader = new CSVReader(Files.newBufferedReader(file.toPath()));
        csv.setCsvReader(csvReader);
        csv.setMappingStrategy(columnMapping(parseClass));
        return csv.parse();
    }

    private static <T> ColumnPositionMappingStrategy<T> columnMapping(Class<T> parseClass) {
        ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(parseClass);
        String[] columns = new String[]{"title", "description", "price"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
