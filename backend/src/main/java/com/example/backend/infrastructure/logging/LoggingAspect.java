package com.example.backend.infrastructure.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(com.example.backend.infrastructure.logging.Loggable)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Loggable annotation = method.getAnnotation(Loggable.class);

        long start = System.currentTimeMillis();
        Object result;

        try {
            if (annotation.logArgs()) {
                log(annotation.level(), "Called method: " + method.getName() +
                        " with args: " + Arrays.toString(joinPoint.getArgs()));
            }

            result = joinPoint.proceed();

            if (annotation.logResult()) {
                log(annotation.level(), "Method: " + method.getName() +
                        " returned: " + result);
            }

            return result;

        } catch (Throwable ex) {
            log(LogLevel.ERROR, "Exception in method: " + method.getName() +
                    " -> " + ex.getMessage());
            throw ex;
        } finally {
            if (annotation.logExecutionTime()) {
                long time = System.currentTimeMillis() - start;
                log(annotation.level(), "Execution time of " + method.getName() + ": " + time + "ms");
            }
        }
    }

    private void log(LogLevel level, String message) {
        String timestamp = Instant.now().toString();
        switch (level) {
            case INFO -> log.info("[{}][INFO] {}", timestamp, message);
            case DEBUG -> log.debug("[{}][DEBUG] {}", timestamp, message);
            case ERROR -> log.error("[{}][ERROR] {}", timestamp, message);
        }
    }
}
