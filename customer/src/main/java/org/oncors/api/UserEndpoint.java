package org.oncors.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.oncors.exception.CompanyNotFoundException;
import org.oncors.exception.UserNotFoundException;
import org.oncors.dto.UserDTO;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/users")
public class UserEndpoint {
    private final UserRepository userRepository;
    private final ModelMapper mapper = configureMapper();

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {

        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(users.stream().map(value -> mapper.map(value, UserDTO.class)).collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            User user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new)).get();
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<User> posUser(@Valid @RequestBody UserDTO newUser) {
        User user = mapper.map(newUser, User.class);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @PutMapping
    public ResponseEntity<User> alterUser(@PathVariable Long id, @Valid @RequestBody User alterUser) {
        try {
            User user = Optional.ofNullable(userRepository.findById(id).orElseThrow(UserNotFoundException::new)).get();
            user.setAddress(alterUser.getAddress());
            user.setCompany(alterUser.getCompany());
            user.setContact(alterUser.getContact());
            user.setPersonalData(alterUser.getPersonalData());
            return ResponseEntity.ok(user);

        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody JsonPatch patch) {
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
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
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

    private ModelMapper configureMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }
}
