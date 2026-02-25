package com.prasad.ratelimiter.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private boolean status;
    private String message;
    private T data;
    private LocalDateTime timeStamp;

    public ApiResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
