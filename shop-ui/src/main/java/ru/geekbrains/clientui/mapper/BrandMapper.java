package ru.geekbrains.clientui.mapper;


import org.mapstruct.Mapper;
import ru.geekbrains.clientui.dto.BrandDto;
import ru.geekbrains.shopdb.model.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandDto brandDto);

    BrandDto toDto(Brand brand);
}
