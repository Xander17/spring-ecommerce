package ru.geekbrains.adminui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shopdb.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto {

    private Integer id;

    private String name;

    private String contentType;
}
