package com.backend.api.repository;

import com.backend.api.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    List<Monitor> findByPollPeriodSeconds(int seconds);

    List<Monitor> findAllByUserId(Long userId);
}
