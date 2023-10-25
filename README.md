# Order Service
## Introduction
The Order Service is a microservice designed to place orders online for shopping apps. It communicates synchronously with the Inventory Service, which is distributed across multiple instances. The Order Service will dynamically discover available Inventory Service instances using the service discovery configuration.

## Architecture
Here's a brief explanation of the architecture of a Order Service:

### Inter Process Communication
It uses inter-process synchronous communication with the Inventory Service to ensure that the available inventory is up-to-date and can fulfill incoming orders.

![Inter Process Communication](https://github.com/abhishekjain1416/order-service/assets/142833334/c3822a76-49ec-45d8-977e-bac19f8013ab)


### Communication with Service Discovery
Configured the service discovery mechanism to obtain the IP addresses or endpoints of available Inventory Service instances.

![Communication with Service Discovery](https://github.com/abhishekjain1416/order-service/assets/142833334/710f23f9-b4b9-4985-9efc-c1e4b54c6ea5)
