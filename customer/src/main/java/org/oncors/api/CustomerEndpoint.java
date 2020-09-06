package org.oncors.api;

import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.oncors.aop.Trace;
import org.oncors.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/customers")
public class CustomerEndpoint {
    @Value("${auth.service.url}")
    String authUrl;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    @Trace
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers(@RequestHeader HttpHeaders httpHeaders) {
        String hello = makeRequest(httpHeaders);
        throw new NotImplementedException();
    }

    private String makeRequest(HttpHeaders httpHeaders) {
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8082/hello", HttpMethod.GET, entity, String.class);

        log.info("test response: " + response.getBody());

        return "hey";
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer Customer) {
        throw new NotImplementedException();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Customer> putCustomer(@PathVariable long id, @RequestBody Customer customer) {
        throw new NotImplementedException();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable long id) {
        throw new NotImplementedException();
    }

}
