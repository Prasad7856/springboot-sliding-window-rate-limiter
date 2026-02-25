package com.prasad.ratelimiter.controller;

import com.prasad.ratelimiter.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<String>> demo() {

        ApiResponse<String> response = new ApiResponse<>(
                true, "Request processed successfully",
                "Welcome to the Home"
        );
        return ResponseEntity.ok(response); // 200 ok
    }
}
