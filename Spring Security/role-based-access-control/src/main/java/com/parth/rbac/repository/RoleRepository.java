package com.parth.rbac.repository;

import com.parth.rbac.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
   Optional<Role> findByName(String role);
}
