package com.prasad.ratelimiter.limiter;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Component
public class RateLimitConfig {

    // endpoint -> allowed requests per minute
    private final Map<String, Integer> limits = Map.of(
            "/api/v1/login", 3,
            "/api/v1/payment", 2,
            "/api/v1/search", 10,
            "/api/v1/home", 5
    );

    public int getLimit(String path) {
        return limits.getOrDefault(path, 5);
    }
}
