package com.backend.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Ping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitor_id", nullable = false)
    private Monitor monitor;

    private int statusCode;
    private long responseTimeMs;
    private boolean isUp;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Ping(Monitor monitor, int statusCode, long responseTimeMs, boolean isUp) {
        this.monitor = monitor;
        this.statusCode = statusCode;
        this.responseTimeMs = responseTimeMs;
        this.isUp = isUp;
    }
}
