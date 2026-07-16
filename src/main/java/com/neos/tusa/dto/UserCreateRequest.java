package com.neos.tusa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 50, message = "The name should not exceed 50 characters")
        String name
) {
}
