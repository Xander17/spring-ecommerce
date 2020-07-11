package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.adminui.mapper.ProductMapper;
import ru.geekbrains.adminui.services.filters.ProductFilter;
import ru.geekbrains.adminui.services.filters.ProductSpecification;
import ru.geekbrains.shopdb.model.Picture;
import ru.geekbrains.shopdb.model.PictureData;
import ru.geekbrains.shopdb.model.Product;
import ru.geekbrains.shopdb.repo.PictureRepository;
import ru.geekbrains.shopdb.repo.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(ProductFilter filter, Pageable pageable) {
        return repository.findAll(ProductSpecification.get(filter), pageable).map(productMapper::toDto);
    }

    @Transactional
    public int save(ProductDto productDto) throws IOException {
        Product product = productMapper.toEntity(productDto);
        List<Picture> pictures = getPicturesList(productDto, product);
        product.setPictures(pictures);
        product = repository.save(product);
        return product.getId();
    }

    private List<Picture> getPicturesList(ProductDto productDto, Product product) throws IOException {
        List<Picture> pictures = product.getPictures();
        MultipartFile[] newPicture = productDto.getNewPicture();
        if (newPicture.length > 0 && newPicture[0].getOriginalFilename() != null && !newPicture[0].getOriginalFilename().isEmpty()) {
            pictures = product.getPictures();
            if (pictures == null) {
                pictures = new ArrayList<>();
            }
            for (MultipartFile file : newPicture) {
                PictureData data = new PictureData();
                data.setData(file.getBytes());
                Picture picture = Picture.builder()
                        .name(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .pictureData(data)
                        .product(product)
                        .build();
                pictures.add(picture);
            }
        }
        return pictures;
    }

    public Optional<ProductDto> findById(int id) {
        return repository.findById(id).map(productMapper::toDto);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
