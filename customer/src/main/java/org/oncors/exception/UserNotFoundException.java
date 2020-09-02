package org.oncors.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(String.format("User not found"));
    }
}
