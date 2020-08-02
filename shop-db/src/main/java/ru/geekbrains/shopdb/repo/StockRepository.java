package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock getStockById(int id);
}
