package com.backend.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping("/public")
    public String allAccess() {
        return "public health up";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User Content with JWT";
    }

    @GetMapping("/special")
    public String specialAcess() {
        return "Special access with JWT";
    }
}
