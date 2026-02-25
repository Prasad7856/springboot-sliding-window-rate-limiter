🚦 Sliding Window Rate Limiter – Spring Boot

      A lightweight, application-level rate limiting service built using the Sliding Window algorithm to control API traffic and prevent abuse.
      
      This project demonstrates how per-client request limiting can be implemented using Spring Boot interceptors and thread-safe concurrent data structures.

📌 Problem Statement

      In production systems, uncontrolled traffic can lead to:
      
      ⚠ API performance degradation
      
      🚫 Abuse of public endpoints
      
      🔁 Repeated retries causing system stress
      
      🔐 Brute-force attempts on authentication APIs
      
      The objective was to design a simple, configurable, and interceptor-based rate limiting mechanism that:
      
      Limits requests per client
      
      Executes before controller logic
      
      Returns HTTP 429 (Too Many Requests) when threshold is exceeded
      
      Ensures thread safety under concurrent access

⚙️ Approach – Sliding Window Algorithm

      Each Client + API Path combination maintains a rolling time window.
      
      For every incoming request:
      
      Expire timestamps older than the configured window duration
      
      Check if the current request count exceeds the allowed threshold
      
      Allow or reject the request accordingly

✅ Guarantees

      Accurate request counting within a moving window
      
      Fair request distribution
      
      Predictable behaviour under load
      
      Protection against burst abuse

🏗 Architecture Overview
<img width="935" height="247" alt="image" src="https://github.com/user-attachments/assets/cd7f364e-b0ca-413e-8fd8-d5cdf72d1ca1" />


The rate limiter executes before controller invocation, making it reusable across all endpoints without modifying business logic.

▶ Running the Application
      1️⃣ Clone the repository
      git clone <your-repo-url>
      cd springboot-sliding-window-rate-limiter
      2️⃣ Run using Maven
      mvn spring-boot:run

Application starts at:

http://localhost:8080

🧪 Testing Rate Limiting

Send multiple rapid requests:

GET http://localhost:8080/api/v1/home
<img width="1920" height="1080" alt="rate limiter success" src="https://github.com/user-attachments/assets/191c11e1-5049-4cf7-bd4b-52d4b9f7cb93" />


After exceeding the configured threshold:
<img width="1920" height="1080" alt="rate limiter too many request" src="https://github.com/user-attachments/assets/17ed5a77-26e1-4011-baa7-f842f9c361a4" />


HTTP 429 - Too Many Requests

🧠 Design Considerations

      Thread safety ensured using synchronized logic inside sliding window processing
      
      In-memory storage used for simplicity and performance
      
      Suitable for single-instance deployments
      
      Clean separation of concerns using interceptor pattern

⚠ Limitations

      In-memory state is lost on application restart
      
      Not horizontally scalable in current form
      
      Not suitable for multi-node deployments without shared storage

🚀 Future Improvements

      Redis-backed distributed rate limiting
      
      Token Bucket implementation for smoother burst handling
      
      Metrics integration (Prometheus)
      
      Authentication-based per-user limiting
      
      Dynamic configuration via properties

🎯 Why This Project

      The goal was to understand traffic control at the application layer, rather than relying entirely on API gateways.
      
      This implementation explores:
      
      🧵 Concurrency handling in Java
      
      🔍 Request interception in Spring Boot
      
      🛡 Defensive backend system design
      
      ⚡ Efficient in-memory request tracking
