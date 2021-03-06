package com.brandon.animalapi.controllers;

import com.brandon.animalapi.security.JWTProvider;
import com.brandon.animalapi.security.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class LoginController {
    private final AuthenticationManager authenticationManager;

    private final JWTProvider jwtProvider;

    public LoginController(AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        String token = jwtProvider.createToken(username);

        return ResponseEntity.ok()
                .header("jwt-token", token)
                .build();
    }

}
