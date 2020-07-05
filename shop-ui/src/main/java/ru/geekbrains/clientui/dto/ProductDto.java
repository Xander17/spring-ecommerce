package ru.geekbrains.clientui.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private Integer id;

    private String title;

    private String description;

    private CategoryDto category;

    private BigDecimal price;

    private List<PictureDto> pictures;
}
