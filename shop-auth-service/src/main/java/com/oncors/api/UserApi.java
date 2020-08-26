package com.oncors.api;

import com.oncors.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
@EnableSwagger2
@RestController
public class UserApi {

    @PostMapping(value = "/create_account")
    public ResponseEntity<Void> createAccount(@RequestBody User createAccount) {
        throw new NotImplementedException();
    }

}
