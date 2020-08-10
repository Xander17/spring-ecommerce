package ru.geekbrains.clientui.service;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.TestUtils;
import ru.geekbrains.clientui.dto.BrandDto;
import ru.geekbrains.shopdb.model.Brand;
import ru.geekbrains.shopdb.repo.BrandRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.geekbrains.TestUtils.getRandomBrand;

@SpringBootTest
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @MockBean
    private BrandRepository brandRepository;

    @Test
    public void findAll() {
        ArrayList<Brand> listFromRepo = Lists.newArrayList(getRandomBrand(1), getRandomBrand(2), getRandomBrand(3));
        List<BrandDto> expectedList = listFromRepo.stream()
                .map(TestUtils::getDtoFromBrand)
                .collect(Collectors.toList());
        when(brandRepository.findAll()).thenReturn(listFromRepo);

        List<BrandDto> brandDtoList = brandService.findAll();

        assertIterableEquals(expectedList, brandDtoList);
    }

    @Test
    public void findAllEmptyCollection() {
        when(brandRepository.findAll()).thenReturn(Collections.emptyList());

        List<BrandDto> categoryDtoList = brandService.findAll();

        assertTrue(categoryDtoList.isEmpty());
    }
}
