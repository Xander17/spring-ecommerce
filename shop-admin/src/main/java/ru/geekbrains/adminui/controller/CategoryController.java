package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.dto.CategoryDto;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;
import ru.geekbrains.adminui.services.CategoryService;
import ru.geekbrains.adminui.services.ImportCsvService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ImportCsvService importCsvService;

    @GetMapping
    public String rolesList(Model model, @RequestParam(name = "editId", required = false) String editId) {
        List<CategoryDto> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("editCategoryId", editId);
        return "categories";
    }

    @PostMapping
    public String add(@RequestParam String newCategory) {
        if (newCategory != null && !newCategory.trim().isEmpty()) {
            CategoryDto category = new CategoryDto();
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
                CategoryDto category = categoryService.findById(idInt).get();
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

    @PostMapping("import")
    public String importCsv(MultipartFile csvFile) throws IOException {
        importCsvService.uploadFile(csvFile, CsvImportType.CATEGORY);
        return "redirect:/categories";
    }
}
