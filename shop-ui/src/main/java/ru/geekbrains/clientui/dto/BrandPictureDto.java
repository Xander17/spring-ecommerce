package ru.geekbrains.clientui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.shopdb.model.PictureData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandPictureDto {

    private Integer id;
    private String contentType;
    private PictureData pictureData;
}
