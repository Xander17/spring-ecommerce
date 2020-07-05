package ru.geekbrains.clientui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.clientui.dto.ProductDto;
import ru.geekbrains.clientui.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String allProducts(Model model) {
        List<ProductDto> products = productService.findAll();

        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable("id") Integer id) {
        ProductDto product = productService.findById(id).orElse(null);
        if (product == null) return "redirect:/shop";
        model.addAttribute("product", product);
        return "product";
    }

}
