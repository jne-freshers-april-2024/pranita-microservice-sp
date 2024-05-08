package com.microservice.currencyconversionservice.controller;

import com.microservice.currencyconversionservice.model.CurrencyConversionModel;
import com.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionModel getConvertedValueByResttemplate(@PathVariable("from") String from , @PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity  ){

        HashMap<String,String> uriVariables = new HashMap<String,String>();
        uriVariables.put("from" , from);
        uriVariables.put("to" , to);
        ResponseEntity<CurrencyConversionModel> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversionModel.class,uriVariables);

        CurrencyConversionModel currencyConversionModel=    responseEntity.getBody();
        return new CurrencyConversionModel(1L,from,to,quantity,quantity.multiply(currencyConversionModel.getConversionMultiple()),currencyConversionModel.getConversionMultiple(),currencyConversionModel.getEnvironment() + " RestTemplate");
    }
    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionModel getConvertedValueByFeign(@PathVariable("from") String from , @PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity  ){

        CurrencyConversionModel currencyConversionModel= currencyExchangeProxy.getExchangeValue(from,to);
        return new CurrencyConversionModel(1L,from,to,quantity,quantity.multiply(currencyConversionModel.getConversionMultiple()),currencyConversionModel.getConversionMultiple(),currencyConversionModel.getEnvironment() +" feign");

    }


}
