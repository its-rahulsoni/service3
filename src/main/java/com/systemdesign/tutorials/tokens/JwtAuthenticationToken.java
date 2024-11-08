package com.systemdesign.tutorials.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(String principal) {
        super(principal, null, null);
    }
}
