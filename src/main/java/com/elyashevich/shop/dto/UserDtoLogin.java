package com.elyashevich.shop.dto;

public record UserDtoLogin(
        String email,
        String username,
        String password
) {}
