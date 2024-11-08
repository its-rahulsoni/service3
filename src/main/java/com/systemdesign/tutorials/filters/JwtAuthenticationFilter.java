package com.systemdesign.tutorials.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.systemdesign.tutorials.controllers.AuthController;
import com.systemdesign.tutorials.tokens.JwtAuthenticationToken;
import com.systemdesign.tutorials.util.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final Logger logger = LogManager.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                    jakarta.servlet.http.HttpServletResponse response,
                                    jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        logger.info("Filter invoked.");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            logger.info("Token is present.");
            String token = authorizationHeader.substring(7);
            try {
                // Validate token and decode claims using java-jwt
                DecodedJWT decodedJWT = jwtUtil.validateToken(token);
                String username = decodedJWT.getSubject();

                if (username != null) {
                    // Set the SecurityContext with authenticated user
                    SecurityContextHolder.getContext().setAuthentication(
                            new JwtAuthenticationToken(username)
                    );
                }
                logger.info("Authentication is successful.");
            } catch (Exception e) {
                // Log the invalid token exception, if necessary
                logger.info("Invalid JWT Token. Authentication is failed.", e.getCause());
            }
        }

        filterChain.doFilter(request, response);
    }
}
