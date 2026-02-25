package com.prasad.ratelimiter.limiter;

public interface RateLimiter {
    boolean allowRequest(String clientId, String path);
}
