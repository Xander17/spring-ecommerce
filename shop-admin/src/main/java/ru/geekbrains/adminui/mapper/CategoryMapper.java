package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.geekbrains.adminui.dto.CategoryDto;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.model.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products", qualifiedByName = "toEntityProduct")
    Category toEntity(CategoryDto categoryDto);

    @Mapping(target = "products", qualifiedByName = "toDtoProduct")
    CategoryDto toDto(Category category);

    @Mapping(target = "category", ignore = true)
    ProductDto toDtoProduct(Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntityProduct(ProductDto productDto);
}
