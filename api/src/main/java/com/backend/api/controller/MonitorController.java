package com.backend.api.controller;

import com.backend.api.model.Monitor;
import com.backend.api.service.MonitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MonitorController {
    private MonitorService monitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMonitor(@RequestBody Monitor monitor){
        monitorService.createMontior(monitor);
    }
}
