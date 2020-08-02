package ru.geekbrains.shopstockservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.shopstockservice.service.StockService;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/count/{id}")
    public Integer getStockCount(@PathVariable("id") Integer id) {
        if (id == null) return 0;
        return stockService.count(id);
    }
}
