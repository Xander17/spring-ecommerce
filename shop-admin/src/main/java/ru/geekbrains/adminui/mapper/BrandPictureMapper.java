package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.adminui.dto.BrandPictureDto;
import ru.geekbrains.shopdb.model.BrandPicture;

@Mapper
public interface BrandPictureMapper {

    @Mapping(target = "pictureData", ignore = true)
    BrandPicture toEntity(BrandPictureDto pictureDto);

    BrandPictureDto toDto(BrandPicture picture);
}
