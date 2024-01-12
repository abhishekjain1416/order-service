package com.programmingtechie.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().components(new Components())
      .info(
        new Info()
        .title("Order Service")
        .description("API Endpoint Decoration")
        .termsOfService("terms")
        .contact(new Contact().email("abhishekjain1416@gmail.com"))
        .license(new License().name("Abhishek Jain"))
        .version("1.0")
      );
  }

}
