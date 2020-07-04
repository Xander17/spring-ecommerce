package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.adminui.dto.RoleDto;
import ru.geekbrains.adminui.mapper.RoleMapper;
import ru.geekbrains.shopdb.model.Role;
import ru.geekbrains.shopdb.model.User;
import ru.geekbrains.shopdb.repo.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<RoleDto> findById(int id) {
        return roleRepository.findById(id).map(roleMapper::toDto);
    }

    @Transactional
    public void save(RoleDto role) {
        roleRepository.save(roleMapper.toEntity(role));
    }

    public void delete(int id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) return;
        List<User> users = role.getUsers();
        users.clear();
        roleRepository.deleteById(id);
    }

}
