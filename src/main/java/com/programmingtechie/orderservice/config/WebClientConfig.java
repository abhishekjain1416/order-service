package com.programmingtechie.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient webClient(){

        // WebClient.builder() creates a WebClient.Builder instance, and .build() builds the WebClient.
        return WebClient.builder().build();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        /**
         * LoadBalanced annotation indicates that this WebClient.Builder should use Ribbon for load balancing.
         * WebClient.builder() creates a WebClient.Builder instance, and it is annotated with @LoadBalanced.
         * It is typically used when making requests to services registered with a service registry e.g. Eureka.
         */
        return WebClient.builder();
    }
}
