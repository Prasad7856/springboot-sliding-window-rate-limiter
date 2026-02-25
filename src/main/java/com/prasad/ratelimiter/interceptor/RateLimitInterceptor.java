package com.prasad.ratelimiter.interceptor;

import com.prasad.ratelimiter.exception.RateLimitExceededException;
import com.prasad.ratelimiter.limiter.RateLimiter;
import com.prasad.ratelimiter.util.ClientIdentifierResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiter rateLimiter;

    public RateLimitInterceptor(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String clientId = ClientIdentifierResolver.resolve(request);
        String path = request.getRequestURI();

        if (!rateLimiter.allowRequest(clientId, path)) {
            throw new RateLimitExceededException("Too many requests");
        }

        return true;
    }
}
