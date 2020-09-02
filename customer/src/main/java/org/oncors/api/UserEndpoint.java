package org.oncors.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.oncors.exception.CompanyNotFoundException;
import org.oncors.exception.UserNotFoundException;
import org.oncors.model.User;
import org.oncors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/users")
public class UserEndpoint {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);

        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCompany(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(user.get()));

    }

    @PostMapping
    public ResponseEntity<User> postCompany(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @PutMapping
    public ResponseEntity<User> alterCompany(@PathVariable Long id, @Valid @RequestBody User newUser) {
        try {
            User user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new)).get();
            user.setAddress(newUser.getAddress());
            user.setCompany(newUser.getCompany());
            user.setContact(newUser.getContact());
            user.setPersonalData(newUser.getPersonalData());
            return ResponseEntity.ok(user);

        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateCompany(@PathVariable long id, @RequestBody JsonPatch patch) {
        try {
            User user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new)).get();
            user = applyPatchToCustomer(patch, user);
            userRepository.save(user);

            return ResponseEntity.ok(user);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteCompany(@PathVariable long id) {
        try {
            User user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new)).get();
            userRepository.delete(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User applyPatchToCustomer(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}
