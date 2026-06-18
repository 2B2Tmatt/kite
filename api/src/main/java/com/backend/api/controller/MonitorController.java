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

import java.util.List;

@RestController
@RequestMapping("/api/public/monitors")
@AllArgsConstructor
public class MonitorController {
    private MonitorService monitorService;

    @PostMapping
    public ResponseEntity<MonitorResponse> createMonitor(@RequestBody @Valid MonitorRequest monitor,
                                                         @AuthenticationPrincipal KiteUserDetails userDetails) {
        MonitorResponse monitorResponse = monitorService.createMonitor(monitor, userDetails.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(monitorResponse);
    }

    @GetMapping
    public ResponseEntity<List<MonitorResponse>> getAllUserMonitors(
            @AuthenticationPrincipal KiteUserDetails userDetails) {
        List<MonitorResponse> resp = monitorService.getAllUserMonitors(userDetails.getId())
                .stream()
                .map(monitor -> new MonitorResponse(
                        monitor.getId(),
                        monitor.getUrl(),
                        monitor.getPollPeriodSeconds(),
                        monitor.isActive(),
                        userDetails.getId()
                ))
                .toList();
        return ResponseEntity.ok(resp);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitorById(@PathVariable Long id) {
        monitorService.deleteMonitorById(id);
        return ResponseEntity.noContent().build();
    }
}
