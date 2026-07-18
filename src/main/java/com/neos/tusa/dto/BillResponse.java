package com.neos.tusa.dto;

import java.math.BigDecimal;

public record BillResponse(
        Long id,
        BigDecimal amount,
        String description,
        Long userId,
        Long partyId
) {
}
