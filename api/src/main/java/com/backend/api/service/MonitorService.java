package com.backend.api.service;

import com.backend.api.model.Monitor;
import com.backend.api.model.Ping;
import com.backend.api.repository.MonitorRepository;
import com.backend.api.repository.PingRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class MonitorService {
    private RestClient client;
    private final PingRepository pingRepository;
    private final MonitorRepository monitorRepository;

    public void createMontior(Monitor monitor) {
        monitorRepository.save(monitor);
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
