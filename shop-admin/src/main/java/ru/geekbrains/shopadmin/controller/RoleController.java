package ru.geekbrains.shopadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.shopadmin.services.RoleService;
import ru.geekbrains.shopadmin.shopdb.model.Role;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public String rolesList(Model model, @RequestParam(name = "editId", required = false) String editId) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("editRoleId", editId);
        return "roles";
    }

    @PostMapping
    public String add(@RequestParam String newRole) {
        if (newRole != null && !newRole.trim().isEmpty()) {
            newRole = newRole.toUpperCase();
            if (!newRole.startsWith("ROLE_")) newRole = "ROLE_" + newRole;
            Role role = new Role();
            role.setName(newRole);
            roleService.save(role);
        }
        return "redirect:/roles";
    }

    @PutMapping
    public String edit(@RequestParam String newName, @RequestParam String id) {
        if (newName != null && id != null && !newName.trim().isEmpty()) {
            try {
                int idInt = Integer.parseInt(id);
                newName = newName.toUpperCase();
                if (!newName.startsWith("ROLE_")) newName = "ROLE_" + newName;
                Role role = roleService.findById(idInt).get();
                role.setName(newName);
                roleService.save(role);
            } catch (NumberFormatException ignored) {
            }
        }
        return "redirect:/roles";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}
