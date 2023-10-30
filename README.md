# Order Service
## Introduction
The Order Service is a microservice designed to place orders online for shopping apps. It communicates synchronously with the Inventory Service, which is distributed across multiple instances with the power of Resilience4j circuit breakers. The Order Service will dynamically discover available Inventory Service instances using the service discovery configuration.

## Architecture
Here's a brief explanation of the architecture of a Order Service:

### Inter Process Communication
It uses inter-process synchronous communication with the Inventory Service to ensure that the available inventory is up-to-date and can fulfill incoming orders.

![Inter Process Communication](https://github.com/abhishekjain1416/order-service/assets/142833334/c3822a76-49ec-45d8-977e-bac19f8013ab)


The issue with synchronous communication lies in the potential unavailability of the Inventory service.
1. It might not be operational or could experience downtime.
2. The Inventory service may exhibit slow response times when interacting with the order service, for any reasons.

Making microservices fault tolerant requires implementing circuit breakers, health checks, timeouts, load balancers. By implementing these mechanisms, microservices can continue operating effectively, even in the presence of failures or disruptions.

### Resilience4j Circuit Breaker
I used resilience4j library to implement circuit breaker pattern which is built on hystrix principles and is integrated with springboot already.

![Resilience4j Circuit Breaker](https://github.com/abhishekjain1416/order-service/assets/142833334/0e9c4375-6b7b-4f09-bd6c-045751a3b021)


### Communication with Service Discovery
Configured the service discovery mechanism to obtain the IP addresses or endpoints of available Inventory Service instances.

![Communication with Service Discovery](https://github.com/abhishekjain1416/order-service/assets/142833334/710f23f9-b4b9-4985-9efc-c1e4b54c6ea5)


## Class Diagram
![UML Class Diagram for Order Service](https://github.com/abhishekjain1416/order-service/assets/142833334/e1002ddb-7f2b-4c51-b187-60e16fdee7d8)

