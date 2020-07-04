package ru.geekbrains.adminui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.adminui.controller.utils.PageNumbers;
import ru.geekbrains.adminui.services.RoleService;
import ru.geekbrains.adminui.services.UserService;
import ru.geekbrains.shopdb.model.User;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final String DEFAULT_LINES_ON_PAGE = "5";
    private final int MAX_NEIGHBOR_PAGE_NUMBERS = 4;

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String userList(Model model,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "pageSize", defaultValue = DEFAULT_LINES_ON_PAGE) Integer pageSize) {

        Page<User> users = userService.findAll(PageRequest.of(page, pageSize));
        model.addAttribute("users", users);
        model.addAttribute("pageNumbers", PageNumbers.get(users.getTotalPages() - 1, users.getNumber(), MAX_NEIGHBOR_PAGE_NUMBERS));
        return "users";
    }

    @GetMapping("add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "user";
    }

    @PostMapping
    public String saveUser(Model model, User user, BindingResult bindingResult) {
        model.addAttribute("allRoles", roleService.findAll());
//        if (bindingResult.hasErrors()) return "user";
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        User user = userService.findById(id).orElse(null);
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
}
