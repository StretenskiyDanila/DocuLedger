package org.example.notificationapp.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private final Counter requestCounter;

    public MetricsService(MeterRegistry registry) {
        requestCounter = Counter.builder("api.requests")
                .description("Total API requests")
                .register(registry);
    }

    public void trackRequest() {
        requestCounter.increment();
    }
}