package com.backend.api.controller;

import com.backend.api.dto.LoginRequest;
import com.backend.api.dto.SignupRequest;
import com.backend.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<String> userSignin(@RequestBody @Valid LoginRequest request) {
        String token = authenticationService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody @Valid LoginRequest request) {
        String token = authenticationService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@RequestBody @Valid SignupRequest request) {
        String message = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }
}
