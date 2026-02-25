package com.prasad.ratelimiter.limiter;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SlidingWindowRateLimiter implements RateLimiter {

    private final Map<String, Deque<Long>> requestStore = new ConcurrentHashMap<>();
    private final long WINDOW_SIZE = 60_000; // 1 min

    private final RateLimitConfig config;

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String clientId, String path) {

        int limit = config.getLimit(path);
        long now = System.currentTimeMillis();

        requestStore.putIfAbsent(clientId + path, new ArrayDeque<>());
        Deque<Long> timestamps = requestStore.get(clientId + path);

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && now - timestamps.peekFirst() > WINDOW_SIZE) {
                timestamps.pollFirst();
            }

            if (timestamps.size() >= limit) {
                return false;
            }

            timestamps.addLast(now);
            return true;
        }
    }
}
