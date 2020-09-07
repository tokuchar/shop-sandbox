package org.oncors.service;

import org.oncors.exception.DataNotFoundException;
import org.oncors.model.entity.User;
import org.oncors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new DataNotFoundException();
        return new ResponseEntity<>(
                users,
                HttpStatus.OK
        );
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
