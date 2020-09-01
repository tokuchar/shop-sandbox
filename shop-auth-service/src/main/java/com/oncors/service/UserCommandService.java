package com.oncors.service;

import com.oncors.model.User;
import com.oncors.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserCommandService implements UserCommand {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(User user) throws UserAlreadyExistsException {
        if (Optional.ofNullable(userRepository.findUserByUsername(user.getUsername())).isPresent()) {
            throw new UserAlreadyExistsException(String.format("user %s already exists", user.getUsername()));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
}
