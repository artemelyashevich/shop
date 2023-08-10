package com.elyashevich.shop.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDto(
        @NotEmpty(message = "FirstName should not be empty")
        @Size(min = 2, message = "Minimum firstName length 2 characters")
        String firstName,
        @NotEmpty(message = "LastName should not be empty")
        @Size(min = 2, message = "Minimum lastName length 2 characters")
        String lastName,
        @NotEmpty(message = "Username should not be empty")
        String username,
        @NotEmpty(message = "Email should not be empty")
        @Email
        String email,
        @NotEmpty(message = "Password should not be empty")
        @Size(min = 4, message = "Minimum password length 4 characters")
        String password
) {}
