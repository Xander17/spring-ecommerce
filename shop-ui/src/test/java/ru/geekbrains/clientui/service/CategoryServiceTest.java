package ru.geekbrains.clientui.service;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.TestUtils;
import ru.geekbrains.clientui.dto.CategoryDto;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.repo.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.geekbrains.TestUtils.getRandomCategory;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void findAll() {
        ArrayList<Category> listFromRepo = Lists.newArrayList(getRandomCategory(1), getRandomCategory(2), getRandomCategory(3));
        List<CategoryDto> expectedList = listFromRepo.stream()
                .map(TestUtils::getDtoFromCategory)
                .collect(Collectors.toList());
        when(categoryRepository.findAll()).thenReturn(listFromRepo);

        List<CategoryDto> categoryDtoList = categoryService.findAll();

        assertIterableEquals(expectedList, categoryDtoList);
    }

    @Test
    public void findAllEmptyCollection() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<CategoryDto> categoryDtoList = categoryService.findAll();

        assertTrue(categoryDtoList.isEmpty());
    }
}
