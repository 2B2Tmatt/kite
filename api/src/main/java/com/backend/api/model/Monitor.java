package com.backend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @URL(message = "Must be a valid URL")
    private String url;

    @Min(value=60)
    private int pollPeriodSeconds;
    private boolean active = true;

    public Monitor(String url, int pollPeriodSeconds, boolean active) {
        this.url = url;
        this.pollPeriodSeconds = pollPeriodSeconds;
        this.active = active;
    }
}
