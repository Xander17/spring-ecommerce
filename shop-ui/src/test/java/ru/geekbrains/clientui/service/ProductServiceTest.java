package ru.geekbrains.clientui.service;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.TestUtils;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Product;
import ru.geekbrains.shopdb.repo.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.geekbrains.TestUtils.getDtoFromProduct;
import static ru.geekbrains.TestUtils.getRandomProduct;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    private static final Integer TEST_PRODUCT_ID = 42;

    @Test
    public void findById() {
        Product productFromRepo = getRandomProduct(TEST_PRODUCT_ID);
        ProductDto expectedDto = getDtoFromProduct(productFromRepo);
        when(productRepository.findById(TEST_PRODUCT_ID)).thenReturn(Optional.of(productFromRepo));

        Optional<ProductDto> productDto = productService.findById(TEST_PRODUCT_ID);

        assertTrue(productDto.isPresent());
        assertEquals(expectedDto, productDto.get());
    }

    @Test
    public void findInvalidId() {
        Integer invalidId = 2;
        when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<ProductDto> productDto = productService.findById(TEST_PRODUCT_ID);

        assertFalse(productDto.isPresent());
    }

    @Test
    public void findAll() {
        ArrayList<Product> listFromRepo = Lists.newArrayList(getRandomProduct(1), getRandomProduct(2), getRandomProduct(3));
        List<ProductDto> expectedList = listFromRepo.stream()
                .map(TestUtils::getDtoFromProduct)
                .collect(Collectors.toList());
        when(productRepository.findAll()).thenReturn(listFromRepo);

        List<ProductDto> productDtoList = productService.findAll();

        assertIterableEquals(expectedList, productDtoList);
    }

    @Test
    public void findAllEmptyCollection() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductDto> productDtoList = productService.findAll();

        assertTrue(productDtoList.isEmpty());
    }
}
