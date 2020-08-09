package ru.geekbrains.adminui.messaging.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.geekbrains.adminui.messaging.csv.*;
import ru.geekbrains.shopdb.model.*;

@Getter
@AllArgsConstructor
public enum CsvImportType {
    PRODUCT(ProductCsv.class, Product.class, "product"),
    CATEGORY(CategoryCsv.class, Category.class, "category"),
    BRAND(BrandCsv.class, Brand.class, "brand"),
    USER(UserCsv.class, User.class, "user"),
    ROLE(RoleCsv.class, Role.class, "role"),

    NONE(null, null, null);

    private Class<?> dtoClass;
    private Class<?> entityClass;
    private String filePrefix;

    public static CsvImportType getTypeByPrefix(String prefix) {
        for (CsvImportType type : values()) {
            if (type.filePrefix.equals(prefix)) {
                return type;
            }
        }
        return NONE;
    }
}
