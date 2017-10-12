# Microservices Problems

Time: 8 minutes

![](https://pbs.twimg.com/media/B04S3VPIAAAwawm.jpg)

### Contextualization

![](http://jonasboner.com/images/posts/bla-bla-microservices-bla-bla/bla_bla_microservices_bla_bla_pdf__page_7_of_31_.png)

- Bulkheading

![](http://www.sportys.com/media/catalog/product/cache/5/image/400x/040ec09b1e35df139433887a97daa66f/1/0/10277_3.jpg)

- Circuit Breaker

### Problemns

- Cascading Failures

![](https://github.com/Netflix/Hystrix/wiki/images/soa-1-640.png)

![](https://github.com/Netflix/Hystrix/wiki/images/soa-2-640.png)

- Single point of Failure

- Fallbacks

- Failure Definition

- Domino Effect

### Failure? What is it?

- Definition of Failure?

![](Try Catch picture)

**Types of Errors**

- 10 Problems in 100 Requests?
- 5% Less Throughput
- Incresing 2% Timeout

# Hystrix

Time: 8 minutes

![](https://camo.githubusercontent.com/e871b5d002a9699e7a2d9fa0178af5c72f0743e0/68747470733a2f2f6e6574666c69782e6769746875622e636f6d2f487973747269782f696d616765732f687973747269782d6c6f676f2d7461676c696e652d3835302e706e67)

### Hystrix History

- Netflix
- Netflix Numbers here

### What is Hystrix

In distributed systems, failures will occur

Hystrix will:

- Isolate points of access between services

- Stop cascading failures across them

- Provide fallback options

- Graceful Degradation

- Domino Effect

- Bulkheading

- Circuit Breaker

- Enable near real-time monitoring, alerting and operational control

This is the result: Hystrix will handle the problems, and we define what is a real problem:

![](https://github.com/Netflix/Hystrix/wiki/images/soa-4-isolation-640.png)

# Hystrix Architecture High Level

Time: 8 minutes

- Circuit Breaker

**Granular**

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

Time: 8 minutes

### Threads and Thread Pool

- Thread Pool Isolation

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/isolation-options-1280.png)

- Isolation

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/request-example-with-latency-1280.png)

### Semaphore

- You can use semaphores (or counters) to limit the number of concurrent calls to any given dependency, instead of using thread pool/queue sizes

- This allows Hystrix to shed load without using thread pools but it does not allow for timing out and walking away

### Request Collapsing

- Without Collapsing

![](https://github.com/alexandregama/hystrix-book/blob/master/Hystrix-Collapsing-1.png?raw=true)

- With Collapsing

![](https://github.com/alexandregama/hystrix-book/blob/master/Hystrix-Collapsing-2.png?raw=true)

### Request Caching

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/request-cache-1280.png)

- Eliminates duplicate thread executions
- Data retrieval is consistent throughout a request.

# Hystrix Metrics

Time: 5 minutes

- Metrics Storage

- Metrics Streaming

- Dashboard Monitoring Real Time

![](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/ops-ab-640.png)

# Code

Time: 10 minutes
