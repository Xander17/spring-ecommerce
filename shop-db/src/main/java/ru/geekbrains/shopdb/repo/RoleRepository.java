package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.shopdb.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
