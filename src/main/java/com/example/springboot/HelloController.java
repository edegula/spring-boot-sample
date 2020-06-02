package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/again")
    public Map<String, String> indexAgain() {
        return Collections.singletonMap("message","Hello from Spring Boot");
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson() {
        // jackson serializer converts the POJO to a JSON object
        return new Person("Dg", "Erwin");
    }

    @RequestMapping(value = "/headers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getHeaders() {
        return new Person("Dg", "Erwin");
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> {
            logger.info(String.format("Header '%s' = %s", key, value));
        });

        return new ResponseEntity<String>(
                String.format("Listed %d headers", headers.size()), HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<User>(new User("Erwin", "Dg", 18), HttpStatus.OK);
    }

}