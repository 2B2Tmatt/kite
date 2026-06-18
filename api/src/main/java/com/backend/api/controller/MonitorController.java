package com.backend.api.controller;

import com.backend.api.dto.MonitorRequest;
import com.backend.api.dto.MonitorResponse;
import com.backend.api.security.KiteUserDetails;
import com.backend.api.service.MonitorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/monitors")
@AllArgsConstructor
public class MonitorController {
    private MonitorService monitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MonitorResponse> createMonitor(@RequestBody @Valid MonitorRequest monitor,
                                                         @AuthenticationPrincipal KiteUserDetails userDetails) {
        MonitorResponse monitorResponse = monitorService.createMonitor(monitor, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(monitorResponse);
    }
}
