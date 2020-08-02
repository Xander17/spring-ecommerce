package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.controller.utils.PageNumbers;
import ru.geekbrains.adminui.controller.utils.UrlParamsFilter;
import ru.geekbrains.adminui.dto.ProductDto;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;
import ru.geekbrains.adminui.services.CategoryService;
import ru.geekbrains.adminui.services.ImportCsvService;
import ru.geekbrains.adminui.services.ProductService;
import ru.geekbrains.adminui.services.filters.ProductFilter;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Value("${app.zuul-server}")
    private String proxyServer;

    private final String DEFAULT_LINES_ON_PAGE = "5";
    private final int MAX_NEIGHBOR_PAGE_NUMBERS = 4;

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImportCsvService importCsvService;

    @GetMapping
    public String productList(
            Model model,
            Optional<ProductFilter> productFilter,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_LINES_ON_PAGE) Integer pageSize) {
        ProductFilter filter = productFilter.orElse(new ProductFilter());
        Page<ProductDto> products = productService.findAll(filter, PageRequest.of(page, pageSize));
        model.addAttribute("products", products);
        model.addAttribute("productFilter", filter);
        model.addAttribute("filterUrl", "products?" + UrlParamsFilter.get(filter));
        model.addAttribute("pageNumbers", PageNumbers.get(products.getTotalPages() - 1, products.getNumber(), MAX_NEIGHBOR_PAGE_NUMBERS));
        return "products";
    }

    @GetMapping("add")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("proxyServer", proxyServer);
        return "product";
    }

    @PostMapping
    public String saveProduct(@Valid ProductDto product, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) return "product";
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        ProductDto product = productService.findById(id).orElse(null);
        if (product == null) return "redirect:/products";
        model.addAttribute("product", product);
        model.addAttribute("allCategories", categoryService.findAll());
        model.addAttribute("proxyServer", proxyServer);
        return "product";
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(Model model, @PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @PostMapping("import")
    public String importCsv(MultipartFile csvFile) throws IOException {
        importCsvService.uploadFile(csvFile, CsvImportType.PRODUCT);
        return "redirect:/products";
    }
}
