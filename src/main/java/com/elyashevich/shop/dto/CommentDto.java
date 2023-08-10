package com.elyashevich.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CommentDto(
        @NotEmpty(message = "Title should not be empty")
        @Size(min = 2, message = "Minimum title length 2 characters")
        String title,
        @NotEmpty(message = "Content should not be empty")
        @Size(min = 2, message = "Minimum content length 1 character")
        String content,
        @NotEmpty(message = "Product id should not be empty")
        Long product,
        @NotEmpty(message = "User id should not be empty")
        Long user
) {
}
