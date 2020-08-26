package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.User;
import org.oncors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/users")
public class UserEndpoint {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getusers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public ResponseEntity<User> putUser(@PathVariable Long id, @Valid @RequestBody User user) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }
}
