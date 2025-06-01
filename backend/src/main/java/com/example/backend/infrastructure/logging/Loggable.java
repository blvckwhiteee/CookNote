package com.example.backend.infrastructure.logging;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
    LogLevel level() default LogLevel.INFO;
    boolean logResult() default true;
    boolean logArgs() default true;
    boolean logExecutionTime() default false;
}

