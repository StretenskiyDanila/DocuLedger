package org.example.businesspack.metrics;

import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.exporter.PushGateway;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class JavaFxMetrics {
    private final PrometheusMeterRegistry registry;
    private final PushGateway pushGateway;
    private final static String JOB_NAME = "javafx_client";

    public JavaFxMetrics() {
        registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        pushGateway = new PushGateway("localhost:9091");  // Адрес Pushgateway
    }

    public void trackOperation(String name, long durationMs) {
        Timer.builder(name)
                .register(registry)
                .record(durationMs, TimeUnit.MILLISECONDS);
    }

    public void pushMetrics() {
        try {
            pushGateway.pushAdd(registry.getPrometheusRegistry(), JOB_NAME);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // Дополнительный метод для очистки метрик
    public void clearMetrics() {
        registry.clear();
    }

}
