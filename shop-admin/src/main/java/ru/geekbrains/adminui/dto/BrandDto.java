package ru.geekbrains.adminui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private Integer id;
    @NotBlank
    private String name;
    private BrandPictureDto picture;
    @NotNull
    private MultipartFile newPicture;
}