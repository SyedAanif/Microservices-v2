package com.learn.microservices.v2.currencyexchangeservice.controllers;

import com.learn.microservices.v2.currencyexchangeservice.dto.CurrencyExchange;
import com.learn.microservices.v2.currencyexchangeservice.repo.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable(name = "from") String from,
                                                  @PathVariable(name = "to") String to){
        log.info("retrieveExchangeValue called with FROM:: {} , TO::{}",from,to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find data for FROM:: %s and TO:: %s", from, to)));
        // UNCOMMENT FOR SEEING KUBERNETES PODS
        //String host = environment.getProperty("HOSTNAME");

        String port = String.valueOf(this.environment.getProperty("local.server.port"));
        currencyExchange.setEnvironment(port);
        //currencyExchange.setEnvironment(String.format("Host is %s and Port is %s",host,port));

        return currencyExchange;

    }
}
