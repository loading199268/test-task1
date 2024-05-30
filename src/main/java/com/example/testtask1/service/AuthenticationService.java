package com.example.testtask1.service;

import com.example.testtask1.controller.auth.JwtAuthenticationResponse;
import com.example.testtask1.controller.auth.SignInRequest;
import com.example.testtask1.controller.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {

        private final UserService userService;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;


        /**
         * Аутентификация пользователя
         *
         * @param request данные пользователя
         * @return токен
         */
        public JwtAuthenticationResponse signIn(SignInRequest request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            UserDetails user = userService
                    .userDetailsService()
                    .loadUserByUsername(request.getUsername());

            String jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        }

        public static boolean hasRole(Role role) {
            return SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .stream()
                    .anyMatch(r -> r.getAuthority().equals(role.name()));
        }

        public static String getUserName() {
            return SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName();
        }
    }
