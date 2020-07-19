package ru.geekbrains.clientui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private Integer id;
    private String name;
    private BrandPictureDto picture;
}