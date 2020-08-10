package ru.geekbrains;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import ru.geekbrains.clientui.dto.BrandDto;
import ru.geekbrains.clientui.dto.CategoryDto;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Brand;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

public class TestUtils {

    public static Product getRandomProduct(Integer id) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(RandomStringUtils.randomAlphabetic(10));
        product.setDescription(RandomStringUtils.randomAlphanumeric(100));
        product.setCategory(getRandomCategory(1));
        product.setPictures(Collections.emptyList());
        product.setPrice(BigDecimal.valueOf(RandomUtils.nextDouble() * 1000)
                .setScale(2, RoundingMode.HALF_UP));
        return product;
    }

    public static ProductDto getRandomProductDto(Integer id) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setTitle(RandomStringUtils.randomAlphabetic(10));
        productDto.setDescription(RandomStringUtils.randomAlphanumeric(100));
        productDto.setCategory(getRandomCategoryDto(1));
        productDto.setPictures(Collections.emptyList());
        productDto.setStock(RandomUtils.nextInt(1000));
        productDto.setPrice(BigDecimal.valueOf(RandomUtils.nextDouble() * 1000)
                .setScale(2, RoundingMode.HALF_UP));
        return productDto;
    }

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(getDtoFromCategory(product.getCategory()));
        productDto.setPictures(Collections.emptyList());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public static Category getRandomCategory(Integer id) {
        Category category = new Category();
        category.setId(id);
        category.setName(RandomStringUtils.randomAlphabetic(20));
        return category;
    }

    public static CategoryDto getRandomCategoryDto(Integer id) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(RandomStringUtils.randomAlphabetic(20));
        return categoryDto;
    }

    public static CategoryDto getDtoFromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public static Brand getRandomBrand(Integer id) {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setName(RandomStringUtils.randomAlphabetic(15));
        return brand;
    }

    public static BrandDto getRandomBrandDto(Integer id) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(id);
        brandDto.setName(RandomStringUtils.randomAlphabetic(15));
        return brandDto;
    }

    public static BrandDto getDtoFromBrand(Brand brand) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setName(brand.getName());
        return brandDto;
    }

}
