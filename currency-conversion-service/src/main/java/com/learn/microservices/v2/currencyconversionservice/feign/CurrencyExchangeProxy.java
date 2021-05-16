package com.learn.microservices.v2.currencyconversionservice.feign;

import com.learn.microservices.v2.currencyconversionservice.dto.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service",url = "http://localhost:8000")
@FeignClient(name = "currency-exchange-service")
//UNCOMMENT THIS IF WE USE SERVICE DISCOVERY OF KUBERNETES BY ENVIRONMENT VARIABLE USE
//DEFAULT ENVIRONMENT VARIABLE
//@FeignClient(name = "currency-exchange-service", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
//CUSTOM ENVIRONMENT VARIABLE
//@FeignClient(name = "currency-exchange-service", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable(name = "from") String from,
                                                    @PathVariable(name = "to") String to);
}
