package com.example.testtask1.repo;

import com.example.testtask1.controller.user.Role;
import com.example.testtask1.repo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(Role role);
}
