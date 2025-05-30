package com.example.backend.infrastructure.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogListeningController {

    @Autowired
    private final LogListener logListener;
    private final EventBus eventBus;

    @PostMapping("/subscribe")
    public void startListening() {
        logListener.subscribe(eventBus);
    }

    @DeleteMapping("/unsubscribe")
    public void stopListening() {
        logListener.unsubscribe();
    }
}
