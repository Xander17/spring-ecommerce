package ru.geekbrains.adminui.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Integer id;

    //    @NotBlank(message = "Fill the title field")
    private String title;

//    @NotBlank(message = "Fill the description field")
    private String description;

    private CategoryDto category;

    //    @NotNull(message = "Fill the price field")
//    @Positive(message = "Price should be a positive")
    private BigDecimal price;

    private MultipartFile[] newPicture;
}
