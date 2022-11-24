package com.appreservas.auth.controller;

import com.appreservas.auth.model.LoginRequest;
import com.appreservas.auth.model.SecurityUser;
import com.appreservas.auth.model.User;
import com.appreservas.auth.repository.UserRepository;
import com.appreservas.auth.service.JpaUserDetailsService;
import com.appreservas.auth.service.TokenService;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JpaUserDetailsService jpaUserDetailsService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JpaUserDetailsService jpaUserDetailsService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        return tokenService.generateToken(authentication, jpaUserDetailsService.loadUserByUsername(userLogin.username()));
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        User userCreated = userRepository.save(newUser);
        return userCreated.toString();
    }
}

