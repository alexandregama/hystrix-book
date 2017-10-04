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
- In general a circuit breaker implements three types of state: open, half-open, and closed:

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

### Threads and Thread Pool

Hystrix uses separate, per-dependency thread pools as a way of constraining any given dependency so latency on the underlying executions will saturate the available threads only in that pool.

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/request-example-with-latency-1280.png)

- Thread Pool Isolation

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/isolation-options-1280.png)

### Semaphores

- You can use semaphores (or counters) to limit the number of concurrent calls to any given dependency, instead of using thread pool/queue sizes
- This allows Hystrix to shed load without using thread pools but it does not allow for timing out and walking away

**Note**: if a dependency is isolated with a semaphore and then becomes latent, the parent threads will remain blocked until the underlying network calls timeout.

### Request Collapsing

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/collapser-1280.png)

Use request collapsing to reduce the number of threads and network connections needed to perform concurrent HystrixCommand executions

By pushing the collapsing logic down to the Hystrix layer, it doesn’t matter how you create the object model, in what order the calls are made, or whether different developers know about optimizations being done or even needing to be done.

The getSomeAttribute() method can be put wherever it fits best and be called in whatever manner suits the usage pattern and the collapser will automatically batch calls into time windows.

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/collapser-flow-1280.png)

### Request Caching

HystrixCommand and HystrixObservableCommand implementations can define a cache key which is then used to de-dupe calls within a request context in a concurrent-aware manner.

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/request-cache-1280.png)

- Data retrieval is consistent throughout a request.
- Eliminates duplicate thread executions.

# Tips

- There is no way to stop latent threads

Please note that there's no way to force the latent thread to stop work - the best Hystrix can do on the JVM is to throw it an InterruptedException. If the work wrapped by Hystrix does not respect InterruptedExceptions, the thread in the Hystrix thread pool will continue its work, though the client already received a TimeoutException. This behavior can saturate the Hystrix thread pool, though the load is 'correctly shed'. Most Java HTTP client libraries do not interpret InterruptedExceptions. So make sure to correctly configure connection and read/write timeouts on the HTTP clients.

- Writing Fallbacks with Network

Write your fallback to provide a generic response, without any network dependency, from an in-memory cache or by means of other static logic. If you **must use a network call in the fallback**, you should do so by means of another HystrixCommand or HystrixObservableCommand.

It is a poor practice to implement a fallback implementation that can fail. You should implement your fallback such that it is not performing any logic that could fail.

# Hystrix Operations

Hystrix is not only a tool for resilience engineering but also for operations.

![](https://github.com/Netflix/Hystrix/wiki/images/thread-configuration-640.png)

Granularity of Problems:

- Client machine garbage collection (your machine does a garbage collection in the middle of a request)
- Service machine garbage collection (the remote server does a garbage collection in the middle of a request to it)
network issues
- Different payload sizes for different request arguments
cache misses
- Bursty call patterns
- New machines starting up (deployments, auto-scale events) and “warming up”

This is one of the reasons why the circuit breaker exists — to “release the pressure” on underlying systems to let them recover instead of pounding them with more requests in retry loops, hung connections, and the like.

### Dependency Failure with Fallback

![](https://github.com/Netflix/Hystrix/wiki/images/ops-getbookmarks-640.png)

### Cascading Dependency Failures

![](https://github.com/Netflix/Hystrix/wiki/images/ops-ab-640.png)

# Metrics Storage

Here's an illustration of commands executing and writing metrics :

![](https://github.com/Netflix/Hystrix/wiki/images/metrics-generation.png)
