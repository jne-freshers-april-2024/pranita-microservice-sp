package com.microservice.currencyexchangeservice.repo;

import com.microservice.currencyexchangeservice.model.CurrencyExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeModel,Long> {

    CurrencyExchangeModel findByFromAndTo(String from,String to);
}
