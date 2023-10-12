package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AuthController {

    @GetMapping("/auth")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}