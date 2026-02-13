package com.carrerconnect.identity_service.controller;

import com.carrerconnect.identity_service.dto.AuthRequest;
import com.carrerconnect.identity_service.entity.User;
import com.carrerconnect.identity_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        System.out.println("DEBUG: Login request for: " + authRequest.getUsername() + " with password: "
                + authRequest.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                System.out.println("DEBUG: Authentication successful");
                return service.generateToken(authRequest.getUsername());
            } else {
                System.out.println("DEBUG: Authentication returned false");
                throw new RuntimeException("invalid access");
            }
        } catch (Exception e) {
            System.out.println("DEBUG: Authentication failed with exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
