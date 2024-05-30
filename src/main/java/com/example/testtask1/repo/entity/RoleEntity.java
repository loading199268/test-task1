package com.example.testtask1.repo.entity;

import com.example.testtask1.controller.user.Role;
import com.example.testtask1.controller.user.RoleDTO;
import com.example.testtask1.controller.user.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleDTO toModel() {
        return new RoleDTO(
                id,
                role);
    }
}
