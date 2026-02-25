package com.prasad.ratelimiter.util;

import jakarta.servlet.http.HttpServletRequest;

public class ClientIdentifierResolver {

    public static String resolve(HttpServletRequest request) {
        String user = request.getHeader("X-USER-ID");
        return user != null ? user : request.getRemoteAddr();
    }
}
