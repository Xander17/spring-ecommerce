package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.controller.utils.PageNumbers;
import ru.geekbrains.adminui.dto.UserDto;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;
import ru.geekbrains.adminui.services.ImportCsvService;
import ru.geekbrains.adminui.services.RoleService;
import ru.geekbrains.adminui.services.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final String DEFAULT_LINES_ON_PAGE = "5";
    private final int MAX_NEIGHBOR_PAGE_NUMBERS = 4;

    private final UserService userService;
    private final RoleService roleService;
    private final ImportCsvService importCsvService;

    @GetMapping
    public String userList(Model model,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "pageSize", defaultValue = DEFAULT_LINES_ON_PAGE) Integer pageSize) {

        Page<UserDto> users = userService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("users", users);
        model.addAttribute("pageNumbers", PageNumbers.get(users.getTotalPages() - 1, users.getNumber(), MAX_NEIGHBOR_PAGE_NUMBERS));
        return "users";
    }

    @GetMapping("add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("allRoles", roleService.findAll());
        return "user";
    }

    @PostMapping
    public String saveUser(Model model, UserDto user, BindingResult bindingResult) {
        model.addAttribute("allRoles", roleService.findAll());
//        if (bindingResult.hasErrors()) return "user";
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        UserDto user = userService.findById(id).orElse(null);
        if (user == null) return "redirect:/users";
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAll());
        return "user";
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(Model model, @PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("import")
    public String importCsv(MultipartFile csvFile) throws IOException {
        importCsvService.uploadFile(csvFile, CsvImportType.USER);
        return "redirect:/users";
    }
}
