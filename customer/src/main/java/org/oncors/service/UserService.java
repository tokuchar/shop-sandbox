//package org.oncors.service;
//
//import org.oncors.exception.UserNotFoundException;
//import org.oncors.exception.DataNotFoundException;
//import org.oncors.model.User;
//import org.oncors.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//
//    public ResponseEntity<List<User>> findAll() {
//        List<User> companies = userRepository.findAll();
//        if (companies.isEmpty())
//            throw new DataNotFoundException();
//        return new ResponseEntity<>(
//                companies,
//                HttpStatus.OK
//        );
//    }
//
//    public ResponseEntity<User> findById(Long id) {
//        return new ResponseEntity<>(
//                userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)),
//                HttpStatus.OK
//        );
//    }
//
//    public ResponseEntity<User> deleteById(Long id) {
//        userRepository.deleteById(id);
//        return new ResponseEntity<>(
//                HttpStatus.OK
//        );
//    }
//
//    public ResponseEntity<User> update(Long id, User user) {
//        return null;
//    }
//
//    public ResponseEntity<User> create(User user) {
//        return new ResponseEntity<>(
//                userRepository.save(user),
//                HttpStatus.CREATED
//        );
//    }
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//}
