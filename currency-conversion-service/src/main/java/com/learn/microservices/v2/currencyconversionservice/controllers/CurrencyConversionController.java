package com.learn.microservices.v2.currencyconversionservice.controllers;

import com.learn.microservices.v2.currencyconversionservice.feign.CurrencyExchangeProxy;
import com.learn.microservices.v2.currencyconversionservice.dto.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable(name = "from") String from,
                                                          @PathVariable(name = "to") String to,
                                                          @PathVariable(name = "qty") BigDecimal quantity){

        Map<String, String> uriVariables=new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        CurrencyConversion currencyConversion = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables).getBody();

        return new CurrencyConversion(currencyConversion.getId(), from,to, currencyConversion.getConversionMultiple(),quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
    }

    @GetMapping(path = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion calculateCurrencyConversionUsingFeign(@PathVariable(name = "from") String from,
                                                          @PathVariable(name = "to") String to,
                                                          @PathVariable(name = "qty") BigDecimal quantity){
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from,to, currencyConversion.getConversionMultiple(),quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment());
    }
}
