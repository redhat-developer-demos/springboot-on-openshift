package com.example.hellobootdiscovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author kameshs
 */
@RestController
@Slf4j
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/randomadd")
    public String randomAdd() {
        log.info("Calling Calculator Service: simplecalculator");
        Random random = new Random();
        return calculatorService.add(random.nextInt(),random.nextInt());
    }
}
