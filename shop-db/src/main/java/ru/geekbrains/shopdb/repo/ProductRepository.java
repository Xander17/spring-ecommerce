package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.Product;

@Repository
public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer> {

}
