package com.elyashevich.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProductDto(
        @NotEmpty(message = "Title should not be empty")
        @Size(min = 2, message = "Minimum title length 2 characters")
        String title,
        @NotEmpty(message = "Description should not be empty")
        @Size(min = 5, message = "Minimum description length 5 characters")
        String description,
        Double price,
        @NotEmpty(message = "User id should not be empty")
        String user_id
) {
}
