package com.example.todolistjavabackend.controllers;

import com.example.todolistjavabackend.models.AppUser;
import com.example.todolistjavabackend.models.LoginRequest;
import com.example.todolistjavabackend.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;



@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody LoginRequest loginRequest) {
        AppUser appUser=new AppUser();
        appUser.setPassword(loginRequest.getPassword());
        appUser.setUsername(loginRequest.getUsername());
        appUser.setRole("USER");
        boolean registerUser = authService.register(appUser);

        if (!registerUser) {

        return ResponseEntity.badRequest().body(Map.of("message", "User could not be registered"));

        }

        return ResponseEntity.ok(Map.of("message", "Register successful"));

    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
       return authService.login(loginRequest.getUsername(), loginRequest.getPassword());


    }


    @GetMapping("/me")
    public String getAuthenticatedUser() {



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {

            return "Authenticated user: " + userDetails.getUsername();
        }

        return "No authenticated user found";
    }

}
