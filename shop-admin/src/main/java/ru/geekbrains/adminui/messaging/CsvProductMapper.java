package ru.geekbrains.adminui.messaging;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
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

    private static <T> HeaderColumnNameMappingStrategy<T> columnMapping(Class<T> parseClass) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(parseClass);
//        String[] columns = new String[]{"title", "description", "price"};
//        strategy.setColumnMapping(columns);
        return strategy;
    }
}
