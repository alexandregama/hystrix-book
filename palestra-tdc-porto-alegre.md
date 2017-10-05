# Microservices Problems

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

# Hystrix Architecture

![](http://www.ebaytechblog.com/wp-content/uploads/2015/08/circuit_breaker_state_diagram.gif)




