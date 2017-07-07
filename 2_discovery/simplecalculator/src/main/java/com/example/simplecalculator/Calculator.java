package com.example.simplecalculator;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Calculator {

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/add/{num1}/{num2}", produces = "text/plain")
    public String add(@PathVariable("num1") Integer num1, @PathVariable("num2") Integer num2) {
        String hostname = getHostName();
        int result = (num1 + num2);
        log.info("Product Result:{} executed on Pod {}", result, hostname);
        return String.format("Service Host :%s \n %d + %d = %d", hostname, num1, num2, result);
    }

    private String getHostName() {
        return System.getenv().getOrDefault("HOSTNAME", "Unknown");
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/sub/{num1}/{num2}", produces = "text/plain")
    public String sub(@PathVariable("num1") Integer num1, @PathVariable("num2") Integer num2) {
        String hostname = getHostName();
        int result = (num1 - num2);
        log.info("Product Result:{} executed on Pod {}", result, hostname);
        return String.format("Service Host :%s \n %d - %d = %d", hostname, num1, num2, result);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/mul", produces = "text/plain",
            consumes = "application/json")
    public String mul(@RequestBody String numbersJson) {
        log.debug("Request : {}", numbersJson);
        long product = 1;
        if (numbersJson != null && !StringUtils.isEmpty(numbersJson)) {
            JSONObject jsonObject = new JSONObject(numbersJson);
            JSONArray jsonArray = jsonObject.getJSONArray("numbers");
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    product = product * jsonArray.getLong(i);
                }
            }
        }
        String hostname = getHostName();
        log.info("Product Result:{} executed on Pod {}", product, hostname);
        return String.format("Service Host :%s \n Product  = %d", hostname, product);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/div", produces = "text/plain",
            consumes = "application/json")
    public String div(@RequestBody String numbersJson) {
        log.debug("Request : {}", numbersJson);
        String hostname = getHostName();
        double answer = 0;
        if (numbersJson != null && !StringUtils.isEmpty(numbersJson)) {
            JSONObject jsonObject = new JSONObject(numbersJson);
            JSONArray jsonArray = jsonObject.getJSONArray("numbers");
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    double n = jsonArray.getDouble(i);
                    if (n != 0) {
                        if (answer == 0) {
                            answer = n;
                        } else {
                            answer = answer / n;
                        }
                    } else {
                        answer = 0;
                        log.error("Exception Divide by 0");
                        break;
                    }
                }
            }
        }
        log.info("Divide Result:{} executed on Pod {}", answer, hostname);
        return String.format("Service Host :%s \n Answer = %f", hostname, answer);
    }
}