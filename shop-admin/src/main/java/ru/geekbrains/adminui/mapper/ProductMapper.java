package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.adminui.dto.CategoryDto;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "newPicture", ignore = true)
    @Mapping(target = "category.products", ignore = true)
    ProductDto toDto(Product product);

    @Mapping(target = "category.products", ignore = true)
    Product toEntity(ProductDto productDto);
}
