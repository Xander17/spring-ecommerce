package ru.geekbrains.shopadmin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.shopadmin.shopdb.model.Role;
import ru.geekbrains.shopadmin.shopdb.model.User;
import ru.geekbrains.shopadmin.shopdb.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    public void delete(int id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) return;
        List<User> users = role.getUsers();
        users.clear();
        roleRepository.deleteById(id);
    }

}
