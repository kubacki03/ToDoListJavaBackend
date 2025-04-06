package com.example.todolistjavabackend.security;


import com.example.todolistjavabackend.repositories.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userAppRepository;

    public CustomUserDetailsService(UserRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAppRepository.findAppUserByUsername(email)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole()) // "USER" lub "ADMIN"
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}