package ru.geekbrains.shopstockservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.shopdb.repo.StockRepository;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;

    public Integer count(int id) {
        return repository.getStockById(id).getStock();
    }
}
