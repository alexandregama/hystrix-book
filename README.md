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

# How it Works?

Hystrix Response:

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/hystrix-return-flow.png)

### Getting the Fallback

- When an exception is thrown by construct() or run()
- When the command is short-circuited because the circuit is open
- When the command’s thread pool and queue or semaphore are at capacity
- When the command has exceeded its timeout length

In the case of a HystrixCommand, to provide fallback logic you implement HystrixCommand.getFallback() which returns a single fallback value.

In the case of a HystrixObservableCommand, to provide fallback logic you implement HystrixObservableCommand.resumeWithFallback() which returns an Observable that may emit a fallback value or values.

### Hystrix Circuit Breaker

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/circuit-breaker-1280.png)

# Tips

- There is no way to stop latent threads

Please note that there's no way to force the latent thread to stop work - the best Hystrix can do on the JVM is to throw it an InterruptedException. If the work wrapped by Hystrix does not respect InterruptedExceptions, the thread in the Hystrix thread pool will continue its work, though the client already received a TimeoutException. This behavior can saturate the Hystrix thread pool, though the load is 'correctly shed'. Most Java HTTP client libraries do not interpret InterruptedExceptions. So make sure to correctly configure connection and read/write timeouts on the HTTP clients.

- Writing Fallbacks with Network

Write your fallback to provide a generic response, without any network dependency, from an in-memory cache or by means of other static logic. If you **must use a network call in the fallback**, you should do so by means of another HystrixCommand or HystrixObservableCommand.

It is a poor practice to implement a fallback implementation that can fail. You should implement your fallback such that it is not performing any logic that could fail.
