package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.dto.BrandDto;
import ru.geekbrains.adminui.mapper.BrandMapper;
import ru.geekbrains.shopdb.model.Brand;
import ru.geekbrains.shopdb.model.BrandPicture;
import ru.geekbrains.shopdb.model.PictureData;
import ru.geekbrains.shopdb.repo.BrandPictureRepository;
import ru.geekbrains.shopdb.repo.BrandRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandPictureRepository brandPictureRepository;
    private final BrandMapper brandMapper;

    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<BrandDto> findById(int id) {
        return brandRepository.findById(id).map(brandMapper::toDto);
    }

    @Transactional
    public boolean save(BrandDto brandDto) throws IOException {
        Brand brand = brandMapper.toEntity(brandDto);
        BrandPicture picture = getPicture(brandDto, brand);
        if (picture == null) {
            return false;
        }
        brand.setPicture(picture);
        brandRepository.save(brand);
        return true;
    }

    private BrandPicture getPicture(BrandDto brandDto, Brand brand) throws IOException {
        MultipartFile newPicture = brandDto.getNewPicture();
        if (newPicture.getOriginalFilename() != null && !newPicture.getOriginalFilename().isEmpty()) {
            PictureData data = new PictureData();
            data.setData(newPicture.getBytes());
            return BrandPicture.builder()
                    .contentType(newPicture.getContentType())
                    .pictureData(data)
                    .build();
        } else {
            return brandPictureRepository.findById(brandDto.getPicture().getId()).orElse(null);
        }
    }

    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
