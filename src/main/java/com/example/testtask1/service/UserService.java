package com.example.testtask1.service;

import com.example.testtask1.controller.user.RoleDTO;
import com.example.testtask1.controller.user.UserDTO;
import com.example.testtask1.repo.RoleRepo;
import com.example.testtask1.repo.UserRepo;
import com.example.testtask1.controller.user.Role;
import com.example.testtask1.repo.entity.RoleEntity;
import com.example.testtask1.repo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Transactional
    public UserDTO addRoleOperator(Long userId) {
        RoleEntity roleEntity = roleRepo.findByRole(Role.OPERATOR)
                        .orElseThrow(()-> new IllegalArgumentException(format("role %s not found", Role.OPERATOR)));

        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException(format("User %s not found", userId)));

        userEntity.getRole().add(roleEntity);

        return userEntity.toModel();
    }

    public UserDetailsService userDetailsService() {
        return this::getByUserName;
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    private UserEntity getByUserName(String username) {
        return userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("пользователь не найден"));
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUserName(username);
    }

    public Set<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream().map(UserEntity::toModel)
                .collect(Collectors.toSet());
    }

    public Set<RoleDTO> getAllRoles() {
        return roleRepo.findAll()
                .stream().map(RoleEntity::toModel)
                .collect(Collectors.toSet());
    }


}
