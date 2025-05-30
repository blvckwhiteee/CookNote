package com.example.backend.infrastructure.event;

import org.springframework.stereotype.Component;
import reactor.core.Disposable;

@Component
public class LogListener {
    private boolean handled;
    private Disposable subscription;

    public void subscribe(EventBus bus) {
        if (!handled) {
            System.out.println("Subscribed");
            handled = true;
            this.subscription = bus.listen()
                    .filter(e -> e.startsWith("Getting all ingredients"))
                    .subscribe(event -> System.out.println("Log entry: " + event));
        }
    }

    public void unsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            System.out.println("Unsubscribed");
        }
    }
}

