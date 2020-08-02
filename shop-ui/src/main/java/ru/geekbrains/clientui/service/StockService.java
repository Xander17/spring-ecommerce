package ru.geekbrains.clientui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${app.stock-service.name}", url = "${app.zuul-server}${app.stock-service.count-path}")
public interface StockService {

    @GetMapping("/count/{id}")
    int getCount(@PathVariable("id") int id);
}
