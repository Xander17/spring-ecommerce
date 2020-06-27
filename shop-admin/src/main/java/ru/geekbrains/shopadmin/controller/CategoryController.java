package ru.geekbrains.shopadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.shopadmin.services.CategoryService;
import ru.geekbrains.shopadmin.shopdb.model.Category;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String rolesList(Model model, @RequestParam(name = "editId", required = false) String editId) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("editCategoryId", editId);
        return "categories";
    }

    @PostMapping
    public String add(@RequestParam String newCategory) {
        if (newCategory != null && !newCategory.trim().isEmpty()) {
            Category category = new Category();
            category.setName(newCategory);
            categoryService.save(category);
        }
        return "redirect:/categories";
    }

    @PutMapping
    public String edit(@RequestParam String newName, @RequestParam String id) {
        if (newName != null && id != null && !newName.trim().isEmpty()) {
            try {
                int idInt = Integer.parseInt(id);
                Category category = categoryService.findById(idInt).get();
                category.setName(newName);
                categoryService.save(category);
            } catch (NumberFormatException ignored) {
            }
        }
        return "redirect:/categories";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
