# Microservices Problems

Time: 5 minutes

![](http://jonasboner.com/images/posts/bla-bla-microservices-bla-bla/bla_bla_microservices_bla_bla_pdf__page_7_of_31_.png)

- Bulkheading

![](http://www.sportys.com/media/catalog/product/cache/5/image/400x/040ec09b1e35df139433887a97daa66f/1/0/10277_3.jpg)

- Circuit Breaker

- Cascading Failures

![](https://github.com/Netflix/Hystrix/wiki/images/soa-1-640.png)

![](https://github.com/Netflix/Hystrix/wiki/images/soa-2-640.png)

- Single point of Failure
- Fallbacks
- Failure Definition
- Domino Effect

# Hystrix

### What is Hystrix

Time: 10 minutes

In distributed systems, failures will occur. Hystrix will:

- Isolate points of access between services
- Stop cascading failures across them
- Provide fallback options
- Domino Effect
- Bulkheading
- Circuit Breaker

### What is Hystrix for?

- Give protection from and control over latency and failure from dependencies accessed
- Stop cascading failures in a complex distributed system
- Fail Fast and rapidly recover
- Fallback and graceful degrade when possible
- Enable near real-time monitoring, alerting and operational control

This is the result: Hystrix will handle the problems, and we define what is a real problem:

![](https://github.com/Netflix/Hystrix/wiki/images/soa-4-isolation-640.png)

# Hystrix Architecture High Level

- Circuit Breaker

Granular 

![](http://www.ebaytechblog.com/wp-content/uploads/2015/08/circuit_breaker_state_diagram.gif)

- Flow Chart

**Big Picture**

![](https://github.com/Netflix/Hystrix/wiki/images/hystrix-command-flow-chart-640.png)

**Part 1**

![](https://github.com/alexandregama/hystrix-book/blob/master/Hystrix-1.png?raw=true)

**Part 2**

![](https://github.com/alexandregama/hystrix-book/blob/master/Hystrix-2.png?raw=true)

**Part 3**

![](https://github.com/alexandregama/hystrix-book/blob/master/Hystrix-3.png?raw=true)

# Hystrix Architecture Low Level

### Threads and Thread Pool

- Thread Pool Isolation

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/isolation-options-1280.png)

### Semaphore

- You can use semaphores (or counters) to limit the number of concurrent calls to any given dependency, instead of using thread pool/queue sizes
- This allows Hystrix to shed load without using thread pools but it does not allow for timing out and walking away

### Request Collapsing

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/collapser-1280.png)

### Request Caching

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/request-cache-1280.png)


