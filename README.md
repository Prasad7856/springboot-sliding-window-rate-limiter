Sliding Window Rate Limiter – Spring Boot

A lightweight rate limiting service built using the Sliding Window algorithm to control API traffic and prevent abuse.

This project demonstrates how per-client request limiting can be implemented at the application layer using Spring Boot interceptors and concurrent data structures.

Problem Statement

In production systems, uncontrolled traffic can lead to:

API performance degradation

Abuse of public endpoints

Repeated retries causing system stress

Brute-force attempts on authentication APIs

The goal of this project was to design a simple, configurable rate limiting mechanism that:

Limits requests per client

Works at interceptor level (before controller logic)

Returns HTTP 429 when threshold is exceeded

Is thread-safe for concurrent access
