package com.oncors.service;

import com.oncors.model.User;

public interface UserCommand {
    void createUser(User user) throws UserAlreadyExistsException;
}
