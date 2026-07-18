package com.neos.tusa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AddBillRequest(
        @NotNull(message = "Amount must be specified")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        String description,
        @NotNull(message = "User must be specified") Long userId,
        @NotNull(message = "Party must be specified") Long partyId
) {
}
