package com.prasad.ratelimiter.exception;

import com.prasad.ratelimiter.model.RateLimitResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateLimitExceededException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public RateLimitResponse handleRateLimit(RateLimitExceededException ex) {
        return new RateLimitResponse("RATE_LIMIT_EXCEEDED", ex.getMessage());
    }
}
