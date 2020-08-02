package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.adminui.messaging.csv.ProductCsv;
import ru.geekbrains.shopdb.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CsvImportMapper<ProductCsv, Product> {

    @Mapping(target = "newPicture", ignore = true)
    @Mapping(target = "category.products", ignore = true)
    ProductDto toDto(Product product);

    @Mapping(target = "category.products", ignore = true)
    Product toEntity(ProductDto productDto);

    @Mapping(source = "category", target = "category.id")
    Product fromCsvToEntity(ProductCsv productCsv);
}
