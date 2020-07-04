package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.adminui.dto.CategoryDto;
import ru.geekbrains.adminui.mapper.CategoryMapper;
import ru.geekbrains.shopdb.model.Category;
import ru.geekbrains.shopdb.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDto> findById(int id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Transactional
    public void save(CategoryDto category) {
        categoryRepository.save(categoryMapper.toEntity(category));
    }

    public void delete(int id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null || category.getProducts().size() > 0) return;
        categoryRepository.deleteById(id);
    }
}
