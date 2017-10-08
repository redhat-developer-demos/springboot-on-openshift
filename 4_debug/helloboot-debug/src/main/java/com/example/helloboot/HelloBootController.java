package com.example.helloboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kameshs
 */
@RestController
public class HelloBootController {

    @GetMapping("/whereami")
    public String whereami(@Value("${message.prefix}") String prefix) {
        String host = System.getenv().getOrDefault("HOSTNAME", "localhost");
        String response = String.format("%s from %s", prefix, host);
        return response;
    }
}
