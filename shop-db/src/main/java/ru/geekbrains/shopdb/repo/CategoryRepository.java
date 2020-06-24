package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.shopdb.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
