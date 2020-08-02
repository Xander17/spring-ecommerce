package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.adminui.dto.BrandDto;
import ru.geekbrains.adminui.dto.BrandPictureDto;
import ru.geekbrains.adminui.services.BrandService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    @Value("${app.zuul-server}")
    private String proxyServer;

    private final BrandService brandService;

    @GetMapping
    public String rolesList(Model model) {
        List<BrandDto> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        return "brands";
    }

    @GetMapping("add")
    public String addProduct(Model model) {
        model.addAttribute("brand", new BrandDto());
        model.addAttribute("proxyServer", proxyServer);
        return "brand";
    }

    @GetMapping("edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        BrandDto brand = brandService.findById(id).orElse(null);
        if (brand == null) return "redirect:/brands";
        model.addAttribute("brand", brand);
        model.addAttribute("proxyServer", proxyServer);
        return "brand";
    }

    @PostMapping
    public String saveProduct(@Valid BrandDto brand, BindingResult bindingResult, Integer pictureId) throws IOException {
        if (StringUtils.isEmpty(brand.getNewPicture().getOriginalFilename()) && pictureId == null) {
            bindingResult.rejectValue("newPicture", "", "Picture is required");
        }
        if (bindingResult.hasErrors()) {
            return "brand";
        }
        brand.setPicture(new BrandPictureDto(pictureId, null, null));
        brandService.save(brand);
        return "redirect:/brands";
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(Model model, @PathVariable("id") int id) {
        brandService.delete(id);
        return "redirect:/brands";
    }
}
