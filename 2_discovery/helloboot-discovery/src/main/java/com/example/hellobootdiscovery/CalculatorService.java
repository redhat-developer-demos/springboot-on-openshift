package com.example.hellobootdiscovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CalculatorService {

    private RestTemplate restTemplate;

    public CalculatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String add(int n1,int n2) {
        String response = restTemplate.getForObject("http://simplecalculator/add/{n1}/{n2}",
                String.class, n1, n2);
        log.info("Response:{}", response);
        return response;
    }
}