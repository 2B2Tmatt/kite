package com.backend.api.service;

import com.backend.api.dto.MonitorRequest;
import com.backend.api.dto.MonitorResponse;
import com.backend.api.model.Monitor;
import com.backend.api.model.Ping;
import com.backend.api.repository.MonitorRepository;
import com.backend.api.repository.PingRepository;
import com.backend.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class MonitorService {
    private RestClient client;
    private final PingRepository pingRepository;
    private final MonitorRepository monitorRepository;
    private final UserRepository userRepository;


    public MonitorResponse createMonitor(@RequestBody MonitorRequest request, Long userId) {
        Monitor monitor = new Monitor(request.url(), request.pollPeriodSeconds(), request.active());
        monitor.setUser(userRepository.getReferenceById(userId));
        Monitor saved = monitorRepository.save(monitor);
        return new MonitorResponse(saved.getUrl(),
                saved.getPollPeriodSeconds(),
                saved.isActive(),
                saved.getId());
    }

    @Async
    public void executeMonitorPing(Monitor monitor) {
        long startTime = System.currentTimeMillis();
        try {
            int code = client.get()
                    .uri(monitor.getUrl())
                    .retrieve()
                    .toBodilessEntity()
                    .getStatusCode()
                    .value();
            saveResult(monitor, code, System.currentTimeMillis() - startTime, code == 200);
        } catch (Exception e) {
            saveResult(monitor, 0, System.currentTimeMillis() - startTime, false);
        }
    }

    private void saveResult(Monitor monitor, int code, long responseTimeMs, boolean isUp) {
        Ping ping = new Ping();
        ping.setMonitor(monitor);
        ping.setStatusCode(code);
        ping.setResponseTimeMs(responseTimeMs);
        ping.setUp(isUp);
        pingRepository.save(ping);
    }
}
