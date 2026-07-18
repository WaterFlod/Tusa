package com.neos.tusa;

import lombok.Getter;

@Getter
public class PartyNotFoundException extends RuntimeException {

    private final Long partyId;

    public PartyNotFoundException(Long partyId) {
        super(String.format("Party with this %d id was not found", partyId));
        this.partyId = partyId;
    }

    public PartyNotFoundException(String message, Long partyId) {
        super(message);
        this.partyId = partyId;
    }

    public PartyNotFoundException(String message, Throwable cause, Long partyId) {
        super(message, cause);
        this.partyId = partyId;
    }
}
