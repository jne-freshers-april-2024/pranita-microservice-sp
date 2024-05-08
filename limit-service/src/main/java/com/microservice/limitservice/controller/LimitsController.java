package com.microservice.limitservice.controller;

import com.microservice.limitservice.Model.Limits;
import com.microservice.limitservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    private Configuration configuration;


   @GetMapping("/limits")
    public Limits getLImits(){
    return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
