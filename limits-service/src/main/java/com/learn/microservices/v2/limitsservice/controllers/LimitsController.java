package com.learn.microservices.v2.limitsservice.controllers;

import com.learn.microservices.v2.limitsservice.config.ValueConfiguration;
import com.learn.microservices.v2.limitsservice.dto.LimitsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private ValueConfiguration valueConfiguration;

    @GetMapping(path = "/limits")
    public LimitsValue retrieveLimits(){
        //return new LimitsValue(9, 999);
        return new LimitsValue(valueConfiguration.getMinimum(),
                valueConfiguration.getMaximum());
    }
}
