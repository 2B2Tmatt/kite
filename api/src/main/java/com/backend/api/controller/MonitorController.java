package com.backend.api.controller;

import com.backend.api.model.Monitor;
import com.backend.api.service.MonitorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitors")
@AllArgsConstructor
public class MonitorController {
    private MonitorService monitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMonitor(@RequestBody Monitor monitor){
        monitorService.createMontior(monitor);
    }
}
