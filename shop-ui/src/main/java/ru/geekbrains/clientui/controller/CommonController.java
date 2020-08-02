package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.geekbrains.clientui.service.BrandService;
import ru.geekbrains.clientui.service.CartService;
import ru.geekbrains.clientui.service.CategoryService;

@Component
@RequiredArgsConstructor
public class CommonController {

    @Value("${app.zuul-server}")
    private String proxyServer;

    private final CartService cartService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public void addRequiredModelAttributes(Model model) {
        model.addAttribute("cartSize", cartService.getCartSize());
        model.addAttribute("cartSum", cartService.getRecalculatedSum());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("proxyServer", proxyServer);
    }
}
