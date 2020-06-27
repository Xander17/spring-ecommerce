package ru.geekbrains.shopadmin.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopadmin.shopdb.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
