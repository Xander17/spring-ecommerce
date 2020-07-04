package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.adminui.mapper.ProductMapper;
import ru.geekbrains.adminui.services.filters.ProductFilter;
import ru.geekbrains.adminui.services.filters.ProductSpecification;
import ru.geekbrains.shopdb.model.Product;
import ru.geekbrains.shopdb.repo.ProductRepository;

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
    public int save(ProductDto product) {
        Product productNew = repository.save(productMapper.toEntity(product));
        return productNew.getId();
    }

    public Optional<ProductDto> findById(int id) {
        return repository.findById(id).map(productMapper::toDto);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
