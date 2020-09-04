package org.oncors.api;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;
import org.apache.http.client.methods.RequestBuilder;
import org.oncors.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8082/hello")
                .build(Collections.emptyMap());
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        Request.Builder requestBuilder = new Request.Builder()
                .url("http://localhost:8082/hello");

        tracer.inject(
                tracer.activeSpan().context(),
                Format.Builtin.HTTP_HEADERS,
                new RequestBuilderCarrier(requestBuilder));

        OkHttpClient httpClient = new OkHttpClient();
        try {
            Response response = httpClient
                    .newCall(requestBuilder.build())
                    .execute();

        } catch (IOException e) {
            e.printStackTrace();
        }


//        tracer.inject(
//                tracer.activeSpan().context(),
//                Format.Builtin.HTTP_HEADERS,
//                new RequestBuilderCarrier(httpHeaders));

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
        private final Request.Builder builder;

        RequestBuilderCarrier(Request.Builder builder) {
            this.builder = builder;
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            throw new UnsupportedOperationException("carrier is write-only");
        }

        @Override
        public void put(String key, String value) {
            builder.addHeader(key, value);
        }
    }
}
