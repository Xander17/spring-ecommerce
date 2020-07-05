package ru.geekbrains.clientui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.clientui.mapper.ProductMapper;
import ru.geekbrains.shopdb.repo.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return repository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(int id) {
        return repository.findById(id).map(productMapper::toDto);
    }
}
