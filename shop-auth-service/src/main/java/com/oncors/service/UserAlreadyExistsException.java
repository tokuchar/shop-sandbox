package com.oncors.service;

import com.oncors.model.User;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(User user) {
    }
}

