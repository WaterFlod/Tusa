package com.neos.tusa;

import lombok.Getter;

@Getter
public class UserNotInPartyException extends RuntimeException {

    private final Long partyId;
    private final Long userId;

    public UserNotInPartyException(Long userId, Long partyId) {
        super(String.format("User with id %d is not a member of party with id %d", userId, partyId));
        this.userId = userId;
        this.partyId = partyId;
    }

    public UserNotInPartyException(String message, Long userId, Long partyId) {
        super(message);
        this.userId = userId;
        this.partyId = partyId;
    }

    public UserNotInPartyException(String message, Throwable cause, Long userId, Long partyId) {
        super(message, cause);
        this.userId = userId;
        this.partyId = partyId;
    }
}
