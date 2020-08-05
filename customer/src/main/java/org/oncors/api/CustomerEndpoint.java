package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerEndpoint {

    @PostMapping
    public String helloWorld() {
        log.info("hello world");
        return "hello world";
    }
}
