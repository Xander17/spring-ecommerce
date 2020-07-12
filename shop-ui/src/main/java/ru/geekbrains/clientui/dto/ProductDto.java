package ru.geekbrains.clientui.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 2523876094627699855L;

    private Integer id;

    private String title;

    private String description;

    private CategoryDto category;

    private BigDecimal price;

    private List<PictureDto> pictures;
}
