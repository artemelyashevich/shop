package com.elyashevich.shop.controller;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.dto.UserDtoLogin;
import com.elyashevich.shop.service.impl.AuthServiceImpl;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public String login(final @RequestBody UserDtoLogin userDto) {
        return authService.createAuthToken(userDto);
    }

    @PermitAll()
    @PostMapping("/register")
    public String register(final @RequestBody UserDto userDto) {
        return authService.createNewUser(userDto);
    }
}
