package com.neos.tusa.dto;

import jakarta.validation.constraints.NotBlank;

public record PartyCreateRequest(
        @NotBlank(message = "The name cannot be empty")
        String name,
        @NotBlank(message = "Admin must be specified")
        Long adminUserId
) {
}
