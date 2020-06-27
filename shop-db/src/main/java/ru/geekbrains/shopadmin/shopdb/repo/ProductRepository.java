package ru.geekbrains.shopadmin.shopdb.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopadmin.shopdb.model.Product;

@Repository
public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer> {

}
