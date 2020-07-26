package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.shopdb.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
