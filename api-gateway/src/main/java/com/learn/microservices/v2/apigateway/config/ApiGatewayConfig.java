package com.learn.microservices.v2.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder){

        Function<GatewayFilterSpec, UriSpec> filterFunction=
                gatewayFilterSpec -> gatewayFilterSpec
                        .addRequestHeader("CUSTOM-HEADERS", "OUR-CUSTOM-VALUE")
                .addRequestParameter("CUSTOM-PARAM", "PARAM-VALUE");
        Function<PredicateSpec, Buildable<Route>> routeFunction =
                predicateSpec -> predicateSpec.path("/get") // IF THIS URI HIT
                        .filters(filterFunction) //HEADER or PATH PARAMS
                        .uri("http://httpbin.org"); //REDIRECT TO THIS LOCATION
        return routeLocatorBuilder.routes()
                .route(routeFunction)
                .route(p -> p.path("/currency-exchange/**") // ANY PATH STARTING WITH PATTERN
                        .uri("lb://currency-exchange-service")) // LOAD BALANCE(lb) AFTER GETTING FROM EUREKA NAMING SERVER
                .route(p -> p.path("/currency-conversion/**") // ANY PATH STARTING WITH PATTERN
                        .uri("lb://currency-conversion-service")) // LOAD BALANCE(lb) AFTER GETTING FROM EUREKA NAMING SERVER
                .route(p -> p.path("/currency-conversion-feign/**") // ANY PATH STARTING WITH PATTERN
                        .uri("lb://currency-conversion-service")) // LOAD BALANCE(lb) AFTER GETTING FROM EUREKA NAMING SERVER
                .build();
    }
}
