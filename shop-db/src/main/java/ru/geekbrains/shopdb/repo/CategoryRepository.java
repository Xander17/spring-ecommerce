package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
