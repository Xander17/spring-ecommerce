package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.adminui.services.filters.ProductFilter;
import ru.geekbrains.adminui.services.filters.ProductSpecification;
import ru.geekbrains.shopdb.repo.ProductRepository;
import ru.geekbrains.shopdb.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(ProductFilter filter, Pageable pageable) {
        return repository.findAll(ProductSpecification.get(filter), pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    public int save(Product product) {
        Product productNew = repository.save(product);
        return productNew.getId();
    }

    public Optional<Product> findById(int id) {
        return repository.findById(id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
