package ru.geekbrains.clientui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.clientui.dto.BrandPictureDto;
import ru.geekbrains.shopdb.model.BrandPicture;

@Mapper
public interface BrandPictureMapper {

    @Mapping(target = "pictureData", ignore = true)
    BrandPicture toEntity(BrandPictureDto pictureDto);

    BrandPictureDto toDto(BrandPicture picture);
}
