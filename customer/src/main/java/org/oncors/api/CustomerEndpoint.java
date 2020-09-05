package org.oncors.api;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.oncors.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/customers")
public class CustomerEndpoint {
    @Autowired
    Tracer tracer;
    @Value("${auth.service.url}")
    String authUrl;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers(@RequestHeader HttpHeaders httpHeaders) {
        try (Scope scope = tracer.buildSpan("get-customer-handler").startActive(true)) {
            Span span = scope.span();

            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("message", "this is a log message for name " + "tomek");
            span.log(fields);
            span.setTag("cisId", "cisIdBlah2344");

            String hello = makeRequest(httpHeaders);
            throw new NotImplementedException();
        }
    }

    private String makeRequest(HttpHeaders httpHeaders) {
        tracer.inject(
                tracer.activeSpan().context(),
                Format.Builtin.HTTP_HEADERS,
                new RequestBuilderCarrier(httpHeaders));

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

    public class RequestBuilderCarrier implements io.opentracing.propagation.TextMap {
        private final HttpHeaders httpHeaders;

        RequestBuilderCarrier(HttpHeaders httpHeaders) {
            this.httpHeaders = httpHeaders;
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            throw new UnsupportedOperationException("carrier is write-only");
        }

        @Override
        public void put(String key, String value) {
            httpHeaders.add(key, value);
        }
    }
}
