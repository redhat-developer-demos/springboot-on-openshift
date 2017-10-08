package com.example.greeterboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST call that says from where its invoked from
 */
@RestController
public class HelloBootController {

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello JDK9  from %s", System.getenv().getOrDefault("HOSTNAME", "localhost"));
    }
}