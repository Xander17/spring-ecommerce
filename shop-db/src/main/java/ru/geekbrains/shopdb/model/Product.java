package ru.geekbrains.shopdb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Fill the title field")
    private String title;

    @Column
//    @NotBlank(message = "Fill the description field")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @NotNull(message = "Fill the price field")
//    @Positive(message = "Price should be a positive")
    private BigDecimal price;
}
