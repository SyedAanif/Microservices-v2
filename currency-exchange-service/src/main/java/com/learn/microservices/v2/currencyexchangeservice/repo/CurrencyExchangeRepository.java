package com.learn.microservices.v2.currencyexchangeservice.repo;

import com.learn.microservices.v2.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
