package com.microservice.currencyexchangeservice.controller;

import com.microservice.currencyexchangeservice.model.CurrencyExchangeModel;
import com.microservice.currencyexchangeservice.repo.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {
    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    Environment environment;
    @GetMapping("currency-exchange/from/{from}/to/{to}")

    public CurrencyExchangeModel getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to)
    {

//        CurrencyExchangeModel currencyExchangeModel = new CurrencyExchangeModel(1L,from,to, BigDecimal.valueOf(80));
        CurrencyExchangeModel currencyExchangeModel=   currencyExchangeRepository.findByFromAndTo(from,to);

        String port = environment.getProperty("local.server.port");
        currencyExchangeModel.setEnvironment(port);
        currencyExchangeRepository.findAll();
        return currencyExchangeModel;
    }}
