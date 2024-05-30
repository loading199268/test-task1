package com.example.testtask1.controller.user;

import java.util.Set;

public record UserDTO(
        Long id,
        String userName,
        Set<Role> role) {
}
