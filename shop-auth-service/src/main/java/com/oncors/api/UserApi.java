package com.oncors.api;

import com.oncors.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
@EnableSwagger2
@RestController
@RequestMapping(value = "/user")
public class UserApi {

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User createAccount) {
        throw new NotImplementedException();
    }

}
