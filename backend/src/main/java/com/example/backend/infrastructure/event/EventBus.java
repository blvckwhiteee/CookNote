package com.example.backend.infrastructure.event;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class EventBus {
    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final Flux<String> flux = sink.asFlux();

    public void emit(String event) {
        sink.tryEmitNext(event);
    }

    public Flux<String> listen() {
        return flux;
    }
}
