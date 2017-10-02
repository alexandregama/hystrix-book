# Hystrix Overview

### What is Hystrix

In distributed systems, failures will occur. Hystrix will:

- Isolated points of access between services
- Stop cascading failures across them
- Provide fallback options

**At Netflix**

- Tens of billions of thread-isolated are executed
- Hundreds of billions of semaphore-isolated are executed

### What is Hystrix for?

- Give protection from and control over latency and failure from dependencies accessed
- Stop cascading failures in a complex distributed system
- Fail Fast and rapidly recover
- Fallback and graceful degrade when possible
- Enable near real-time monitoring, alerting and operational control

### Hystrix works by

- Preventing any single dependency from using up all container (such as Tomcat) user threads.
- Shedding load and failing fast instead of queueing.
- Providing fallbacks wherever feasible to protect users from failure.
- Using isolation techniques (such as bulkhead, swimlane, and circuit breaker patterns) to limit the impact of any one dependency.
- Optimizing for time-to-discovery through near real-time metrics, monitoring, and alerting
- Optimizing for time-to-recovery by means of low latency propagation of configuration changes and support for dynamic property changes in most aspects of Hystrix, which allows you to make real-time operational modifications with low latency feedback loops.
- Protecting against failures in the entire dependency client execution, not just in the network traffic.

### How does Hystrix accomplish its goals?

- Wrapping all calls to external systems in a **HystrixCommand** or **HystrixObservableCommand**
- Timing-out calls that take longer than thresholds you define
- Maintaning a small thread pool (or semaphore) for each depencency. if it becomes full, requests destined for that dependency will be immediately rejected instead of queued up.
- Measuring successes, failures (exceptions thrown by client), timeouts, and thread rejections.
- Tripping a circuit-breaker to stop all requests to a particular service for a period of time, either manually or automatically if the error percentage for the service passes a threshold.
- Performing fallback logic when a request fails, is rejected, times-out, or short-circuits.
- Monitoring metrics and configuration changes in near real-time.
