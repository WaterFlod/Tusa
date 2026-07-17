package com.neos.tusa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PartyCreateRequest(
        @NotBlank(message = "The name cannot be empty")
        String name,
        @NotNull(message = "Admin must be specified")
        Long adminUserId
) {
}
