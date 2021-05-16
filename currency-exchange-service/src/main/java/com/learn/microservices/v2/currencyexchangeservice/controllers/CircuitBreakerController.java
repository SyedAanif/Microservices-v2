package com.learn.microservices.v2.currencyexchangeservice.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

    @GetMapping(path = "/circuit-breaker")
    //@Retry(name = "default")
    //@Retry(name = "sample-api",fallbackMethod = "gracefulFail") // PICKING CUSTOMISED RETRY FROM YAML
    //@CircuitBreaker(name = "default",fallbackMethod = "gracefulFail") // CIRCUIT BREAKER PATTERN
    //@RateLimiter(name = "default") // SPECIFIC NUMBER OF CALLS IN A SPECIFIC TIME
    @Bulkhead(name = "default") // CONCURRENT NUMBER OF CALLS IN A SPECIFIC TIME
    public String sampleApi(){
        log.info("Sample Api Call Received.....");
        //return new RestTemplate().getForEntity("DUMMY_URL", String.class).getBody();
        return "Playing with Resilience4j....";
    }

    private String gracefulFail(Exception exception){
        return "Fallback Response.....";
    }
}
