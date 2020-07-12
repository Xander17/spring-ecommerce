package ru.geekbrains.clientui.service.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.clientui.dto.ProductDto;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class OrderLineItem implements Serializable {

    private static final long serialVersionUID = -3248569536278118679L;

    @EqualsAndHashCode.Include
    private Integer productId;

    private ProductDto product;

    private int quantity;

    public OrderLineItem(ProductDto product) {
        this.product = product;
        this.productId = product.getId();
    }

    public OrderLineItem(ProductDto product, int qty) {
        this.product = product;
        this.productId = product.getId();
        this.quantity = qty;
    }

    public BigDecimal getSum() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
