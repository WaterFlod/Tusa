package com.neos.tusa;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long userId) {
        super(String.format("User with this %d id was not found", userId));
        this.userId = userId;
    }

    public UserNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public UserNotFoundException(String message, Throwable cause, Long userId) {
        super(message, cause);
        this.userId = userId;
    }
}
