package com.backend.api.service;

import com.backend.api.dto.LoginRequest;
import com.backend.api.dto.SignupRequest;
import com.backend.api.model.User;
import com.backend.api.repository.UserRepository;
import com.backend.api.security.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {


    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        return jwtUtils.generateToken(authentication.getName());
    }

    @Transactional
    public String register(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User already exists");
        }

        User newUser = new User(request.email(), encoder.encode(request.password()));
        userRepository.save(newUser);

        return jwtUtils.generateToken(request.email());
    }

}
