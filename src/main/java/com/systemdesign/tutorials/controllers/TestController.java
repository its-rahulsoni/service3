package com.systemdesign.tutorials.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api1")
public class TestController {

    private static final Logger logger = LogManager.getLogger(TestController.class);

    // A simple GET request
    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Api /hello is invoked to say - Hello, World!.");
        return "Service 03 says - Hello, World!";
    }

}
