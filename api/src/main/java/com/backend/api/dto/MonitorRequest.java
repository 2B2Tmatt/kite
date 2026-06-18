package com.backend.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record MonitorRequest(
        @URL(message = "Must be a valid URL")
        @NotNull(message = "URL cannot be null")
        String url,

        @Min(value = 60, message = "Minimum polling period is 60 seconds")
        int pollPeriodSeconds,

        boolean active
) {
}
