package com.example.testtask1.controller.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {

    @Schema(description = "Имя пользователя", example = "132@mail.ru")
    private String username;

    @Schema(description = "Пароль", example = "33")
    private String password;
}
