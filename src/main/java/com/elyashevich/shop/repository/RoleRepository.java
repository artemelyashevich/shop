package com.elyashevich.shop.repository;

import com.elyashevich.shop.model.role.Role;
import com.elyashevich.shop.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
