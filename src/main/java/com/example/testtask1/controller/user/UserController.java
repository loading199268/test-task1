package com.example.testtask1.controller.user;

import com.example.testtask1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("{userId}/operator")
    @Secured("ADMINISTRATOR")
    public UserDTO addRoleOperator(@PathVariable Long userId) {
        return userService.addRoleOperator(userId);
    }

    @GetMapping("/users")
    @Secured("ADMINISTRATOR")
    public Set<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    @Secured("ADMINISTRATOR")
    public Set<RoleDTO> getAllRoles() {
        return userService.getAllRoles();
    }


}
