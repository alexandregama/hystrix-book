# Main Goals

This is the list of the main goals that should be covered in this book

### Chapter 1 - Microservice Architecture and Fault Tolerance

- Introduction to the problems of having a Microservice Architeture and Failures

- Introduction to Netflix Microservice Architecture

- Introduction to Hystrix

### Chapter 2 - Creating Our Microservice Architecture with 3 Spring Boot Applications

- First App

- Second App

- Third App

### Chapter 3 - How does Hystrix work?

- Flow Chart

- Sequence Diagram

- The Famous Circuit Breaker

- Failure Isolation

- Threads and Thread Pools

- Semaphores

- Request Collapsing

- Request Caching

### Chapter 4 - How to Use Hystrix - Hello World

- The First Hystrix Command

- Syncrhonous and Asyncrhonous Execution

- Reactive Execution

- Reactive Command

- Fallbacks

### Chapter 5 - Error Propagation and Commands

### Chapter 6 - Hystrix Requests

- Request Cache

- Request Collapsing

- Request Context Setup

### Chapter 7 - Hystrix Common Patterns

- Fail Fast

- Fail Silent

- Fallbacks

### Chapter 8 - Working with Microservices Apps

- Failing Fast when service goes down

- Recoverying after service goes up

- Working with Request Collapsing

- Putting Cache in the App

### Chapter 9 - Tuning the Fault-Tolerance and Graceful Degradation

- Configuring the Thread Pool

- Better Networking Timeout

- Caching the Networking

### Chapter 10 - What About Monitoring?

- How does Hystrix Save the Metrics?

- How can we see all failures as a metric?





# Chapter 1

## What Problem Do We Want to Resolve?

- How can we work with several Microservices and its failures?

- If one service stops to respond properly (we will see that you can decide what is "properly"), how can we know that?

- How can we fail fast?

- How does our service can recovery from a service that is responding corretly again?

- How can we measure the Microservices that are ok?

- How can we measure how many times we have failures in our system? But what is a failure?

- How can we measure a failure? Failure is just a system that does not responde anymore?

- Can we responde to another service automatically if the main service fails?

- Can we recover our system gracefully, without stop the whole application?

### What is Hystrix?

### What is Hystrix For?

### What Problem Does Hystrix Solve?

### How Does Hystrix Accomplish its Goals?
