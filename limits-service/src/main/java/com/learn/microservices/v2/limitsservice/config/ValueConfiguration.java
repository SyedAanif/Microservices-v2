package com.learn.microservices.v2.limitsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "limits-service")
@Data
public class ValueConfiguration {

    private int minimum;
    private int maximum;
}
