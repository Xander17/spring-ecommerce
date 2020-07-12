package ru.geekbrains.clientui.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.clientui.service.model.OrderLineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService implements Serializable {

    private static final long serialVersionUID = 2597994061279489144L;
    private final Map<OrderLineItem, Integer> cart = new HashMap<>();

    public void addQuantity(ProductDto product, int addQty) {
        cart.compute(new OrderLineItem(product),
                (item, quantity) -> quantity = quantity == null ? addQty : quantity + addQty);
    }

    public void removeProduct(OrderLineItem item) {
        cart.remove(item);
    }

    public List<OrderLineItem> getCart() {
        cart.forEach(OrderLineItem::setQuantity);
        return new ArrayList<>(cart.keySet());
    }

    public Integer getCartSize() {
        return cart.size();
    }

    public BigDecimal getRecalculatedSum() {
        cart.forEach(OrderLineItem::setQuantity);
        return getSum();
    }

    public BigDecimal getSum() {
        return cart.keySet().stream()
                .map(OrderLineItem::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void update(OrderLineItem item) {
        int quantity = item.getQuantity();
        if (quantity > 0) {
            cart.put(item, quantity);
        } else {
            removeProduct(item);
        }
    }
}
