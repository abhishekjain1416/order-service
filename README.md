# Order Service
## Overview
The Order Service is a microservice designed to place orders online for shopping apps. It communicates synchronously with the Inventory Service, which is distributed across multiple instances with the power of Resilience4j circuit breakers. The Order Service will dynamically discover available Inventory Service instances using the service discovery configuration.

## Architecture
Here's a brief explanation of the architecture of a Order Service:
![architecture-E-Commerce Application drawio](https://github.com/abhishekjain1416/order-service/assets/142833334/971e98d1-eab2-49f8-913c-013c1adca26a)


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


## Prerequisites
Before you can set up and run this system, ensure you have the kafka installed in your local system. You can use Docker Compose to define and run the necessary Docker containers for Confluent components, including Kafka, ZooKeeper.

Create a Docker Compose YAML file to define your Confluent Platform environment. You can create a file named docker-compose.yml with the following content:
~~~
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.0.1
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  broker:
    image: confluentinc/cp-kafka:7.0.1
    container_name: broker
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
~~~

### Steps to get started with Kafka
1. Save the Docker Compose file in a location of your choice.
2. Open your terminal and navigate to the directory where you saved the Docker Compose file.
3. Run the following command to start the Confluent Platform containers:
~~~
docker-compose up -d
~~~
The -d flag runs the containers in the background.

### Stop Kafka

When you're done, you can stop and remove the containers using the following command:
~~~
docker-compose down
~~~
