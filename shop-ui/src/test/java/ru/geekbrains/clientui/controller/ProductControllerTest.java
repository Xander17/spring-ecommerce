package ru.geekbrains.clientui.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.model.Product;
import ru.geekbrains.shopdb.repo.CategoryRepository;
import ru.geekbrains.shopdb.repo.ProductRepository;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.geekbrains.TestUtils.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    private static final String GET_URL = "/shop/product/";
    private static final String GET_ALL_URL = "/shop";

    private static final Integer STOCK = 42;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    public static void setUp() {
        setupStockService();
    }

    private static void setupStockService() {
        WireMockServer wm = new WireMockServer(WireMockConfiguration.options().port(8091).bindAddress("localhost"));
        wm.start();

        wm.stubFor(get(urlMatching("^\\/stock\\/count\\/\\d+$"))
                .willReturn(okJson(String.valueOf(STOCK))));
    }

    @Test
    public void getProduct() throws Exception {
        int testId = 1;
        Product product = getRandomProduct(testId);
        ProductDto expectedProductDto = getDtoFromProduct(product);
        expectedProductDto.setStock(STOCK);

        categoryRepository.save(product.getCategory());
        productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.get(GET_URL + testId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("product", expectedProductDto))
                .andExpect(view().name("product"));
    }

    @Test
    public void getProductWithInvalidId() throws Exception {
        int testId = 42;
        int invalidId = 1000;
        Product product = getRandomProduct(testId);

        categoryRepository.save(product.getCategory());
        productRepository.save(product);

        mockMvc.perform(MockMvcRequestBuilders.get(GET_URL + invalidId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/shop"));
    }

    @Test
    public void getAll() throws Exception {
        List<ProductDto> expectedProductsList = prepareDbAndGetExpectedResultList();

        mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_URL))
                .andExpect(model().attribute("products", expectedProductsList))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("shop"));
    }

    private List<ProductDto> prepareDbAndGetExpectedResultList() {
        Product product1 = getRandomProduct(1);
        Category category1 = getRandomCategory(1);
        product1.setCategory(category1);
        Product product2 = getRandomProduct(2);
        Category category2 = getRandomCategory(2);
        product2.setCategory(category2);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        productRepository.save(product1);
        productRepository.save(product2);

        ProductDto productDto1 = getDtoFromProduct(product1);
        ProductDto productDto2 = getDtoFromProduct(product2);

        return Lists.newArrayList(productDto1, productDto2);
    }
}
