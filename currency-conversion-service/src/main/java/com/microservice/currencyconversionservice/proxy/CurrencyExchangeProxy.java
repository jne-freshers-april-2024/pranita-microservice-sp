package com.microservice.currencyconversionservice.proxy;

import com.microservice.currencyconversionservice.model.CurrencyConversionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionModel getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}