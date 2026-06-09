package com.backend.api.service;

import com.backend.api.model.Monitor;
import com.backend.api.repository.MonitorRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PingScheduler {
    private final MonitorService monitorService;
    private final MonitorRepository monitorRepository;
    private final AsyncTaskExecutor taskExecutor;

    @Scheduled(fixedRate = 600000)
    public void pingOneMinuteMonitors(){
        List<Monitor> oneMinuteMonitors = monitorRepository.findByPollPeriodSeconds(60);
        for(Monitor monitor: oneMinuteMonitors) {
            monitorService.executeMonitorPing(monitor);
        }
    }

    @Scheduled(fixedRate = 300000)
    public void pingFiveMinuteMonitors(){
        List<Monitor> fiveMinuteMonitors = monitorRepository.findByPollPeriodSeconds(300);
        for(Monitor monitor: fiveMinuteMonitors){
            monitorService.executeMonitorPing(monitor);
        }
    }
}
