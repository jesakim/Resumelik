package com.example.resumlik.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull(message = "The email cannot be null")
        @NotBlank(message = "The email cannot be blank")
        String email,
        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        String password
) { }
