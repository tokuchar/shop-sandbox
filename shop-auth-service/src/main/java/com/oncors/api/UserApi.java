package com.oncors.api;

import com.oncors.model.User;
import com.oncors.service.UserCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@RestController
@RequestMapping(value = "/user")
public class UserApi {
    @Autowired
    UserCommandService userCommandService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userCommandService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}