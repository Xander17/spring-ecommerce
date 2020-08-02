package ru.geekbrains.adminui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.adminui.dto.csv.ProductCsv;
import ru.geekbrains.shopdb.model.Product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum CsvImportType {
    PRODUCT(ProductCsv.class, Product.class, "product"),

    NONE(null, null, null);

    private Class<?> dtoClass;
    private Class<?> entityClass;
    private String filePrefix;

    private static Map<String, CsvImportType> prefixMap = new HashMap<>();

    private static void buildMap() {
        prefixMap = Arrays.stream(values())
                .collect(Collectors.toMap(csvImportType -> csvImportType.filePrefix, csvImportType -> csvImportType));
    }

    public static CsvImportType getTypeByPrefix(String prefix) {
        return Optional.ofNullable(prefixMap.get(prefix)).orElse(NONE);
    }

    public static Class<?> getDtoClassByPrefix(String prefix) {
        return Optional.ofNullable(prefixMap.get(prefix)).orElse(NONE).dtoClass;
    }

    public static Class<?> getEntityClassByPrefix(String prefix) {
        return Optional.ofNullable(prefixMap.get(prefix)).orElse(NONE).entityClass;
    }
}
