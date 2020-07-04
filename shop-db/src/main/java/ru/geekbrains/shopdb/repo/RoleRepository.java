package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
