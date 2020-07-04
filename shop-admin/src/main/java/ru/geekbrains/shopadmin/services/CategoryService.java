package ru.geekbrains.shopadmin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.shopadmin.shopdb.model.Category;
import ru.geekbrains.shopadmin.shopdb.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void delete(int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null || category.getProducts().size() > 0) return;
        categoryRepository.deleteById(id);
    }
}
