package com.example.demo.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRouteConfig {
    @Bean
    public RouteLocator accountRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(p -> p.path("/account/**")
                .uri("http://localhost:8081"))
            .route(p -> p.path("/task/**")
                .uri("8083"))
            .build();
    }
}