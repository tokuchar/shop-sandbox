package com.oncors.service;

import com.oncors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCommandService implements UserCommand {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) throws UserAlreadyExist {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
