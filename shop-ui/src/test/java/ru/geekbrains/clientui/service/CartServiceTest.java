package ru.geekbrains.clientui.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.clientui.service.model.OrderLineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.geekbrains.TestUtils.getRandomProductDto;

@SpringBootTest
public class CartServiceTest {

    private CartService cartService;
    private ProductDto testProduct;

    private static final Integer TEST_PRODUCT_ID = 42;

    @BeforeEach
    public void setUp() {
        cartService = new CartService();
        testProduct = getRandomProductDto(TEST_PRODUCT_ID);
    }

    @Test
    public void emptyCart() {
        assertCart(0, BigDecimal.ZERO);
    }

    @Test
    public void addQuantity() {
        int qty = 10;
        cartService.addQuantity(testProduct, qty);
        BigDecimal expectedSum = testProduct.getPrice()
                .multiply(BigDecimal.valueOf(qty));

        assertCart(1, expectedSum);
        assertLineItem(testProduct, qty);
    }

    @Test
    public void addAdditionalQuantity() {
        int qty = 10;
        int additionalQty = 15;
        cartService.addQuantity(testProduct, qty);
        cartService.addQuantity(testProduct, additionalQty);
        BigDecimal expectedSum = testProduct.getPrice()
                .multiply(BigDecimal.valueOf(qty + additionalQty));

        assertCart(1, expectedSum);
        assertLineItem(testProduct, qty + additionalQty);
    }

    @Test
    public void addFewItems() {
        int qty = 10;
        cartService.addQuantity(testProduct, qty);

        ProductDto secondProduct = getRandomProductDto(43);
        int secondQty = 15;
        cartService.addQuantity(secondProduct, secondQty);

        BigDecimal expectedSum = testProduct.getPrice()
                .multiply(BigDecimal.valueOf(qty))
                .add(secondProduct.getPrice()
                        .multiply(BigDecimal.valueOf(secondQty))
                );

        assertCart(2, expectedSum);
        assertLineItem(testProduct, qty);
        assertLineItem(secondProduct, secondQty);
    }

    @Test
    public void changeQuantity() {
        int qty = 10;
        int newQty = 15;
        cartService.addQuantity(testProduct, qty);
        OrderLineItem lineItem = getItemFromCartById(cartService.getCart(), testProduct.getId());
        lineItem.setQuantity(newQty);
        cartService.update(lineItem);
        BigDecimal expectedSum = testProduct.getPrice()
                .multiply(BigDecimal.valueOf(newQty));

        assertCart(1, expectedSum);
        assertLineItem(testProduct, newQty);
    }

    @Test
    public void changeQuantityToZero() {
        int qty = 10;
        int newQty = 0;
        cartService.addQuantity(testProduct, qty);
        OrderLineItem lineItem = cartService.getCart().get(0);
        lineItem.setQuantity(newQty);
        cartService.update(lineItem);

        assertCart(0, BigDecimal.ZERO);
    }

    @Test
    public void removeItem() {
        int qty = 10;
        cartService.addQuantity(testProduct, qty);
        OrderLineItem lineItem = cartService.getCart().get(0);
        cartService.removeProduct(lineItem);

        assertCart(0, BigDecimal.ZERO);
    }

    private void assertCart(Integer expectedCartSize, BigDecimal expectedSum) {
        List<OrderLineItem> cart = cartService.getCart();
        assertEquals(expectedCartSize, cart.size());
        assertEquals(expectedCartSize, cartService.getCartSize());
        assertEquals(expectedSum, cartService.getRecalculatedSum());
    }

    private void assertLineItem(ProductDto product, Integer expectedQty) {
        List<OrderLineItem> cart = cartService.getCart();
        BigDecimal expectedSum = product.getPrice()
                .multiply(BigDecimal.valueOf(expectedQty));
        OrderLineItem lineItem = getItemFromCartById(cart, product.getId());
        assertNotNull(lineItem);
        assertEquals(expectedQty, lineItem.getQuantity());
        assertEquals(expectedSum, lineItem.getSum());
    }

    private OrderLineItem getItemFromCartById(List<OrderLineItem> cart, Integer id) {
        return cart.stream()
                .filter(item -> item.getProductId().equals(id))
                .findFirst().orElse(null);
    }
}
