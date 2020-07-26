package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import ru.geekbrains.adminui.dto.BrandDto;
import ru.geekbrains.shopdb.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandDto brandDto);

    BrandDto toDto(Brand brand);
}
