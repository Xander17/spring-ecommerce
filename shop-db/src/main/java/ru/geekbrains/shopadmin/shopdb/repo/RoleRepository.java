package ru.geekbrains.shopadmin.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopadmin.shopdb.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
