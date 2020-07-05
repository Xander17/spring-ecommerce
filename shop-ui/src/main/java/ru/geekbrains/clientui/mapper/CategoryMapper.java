package ru.geekbrains.clientui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.clientui.dto.CategoryDto;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.model.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products", qualifiedByName = "toEntityProduct")
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    @Mapping(target = "category", ignore = true)
    Product toEntityProduct(ProductDto productDto);
}
