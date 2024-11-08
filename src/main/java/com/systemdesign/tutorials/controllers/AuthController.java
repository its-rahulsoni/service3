package com.systemdesign.tutorials.controllers;

import com.systemdesign.tutorials.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        logger.info("login called with username: {}", username);

        // For demo purposes, using hardcoded user. Replace with actual user validation logic.
        if ("user".equals(username) && "password".equals(password)) {
            logger.info("username and password matched.");

            String token = jwtUtil.generateToken(username);
            logger.info("JWT token generated: {}", token);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } else {
            logger.error("Invalid Credentials.");
            throw new RuntimeException("Invalid credentials");
        }
    }
}
