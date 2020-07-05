package ru.geekbrains.clientui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.clientui.dto.PictureDto;
import ru.geekbrains.shopdb.model.Picture;

@Mapper
public interface PictureMapper {

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "pictureData", ignore = true)
    Picture toEntity(PictureDto pictureDto);

    PictureDto toDto(Picture picture);
}
