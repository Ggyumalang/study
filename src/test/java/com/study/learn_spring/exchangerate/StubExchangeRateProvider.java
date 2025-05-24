package com.study.learn_spring.exchangerate;

import com.study.learn_spring.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class StubExchangeRateProvider implements ExchangeRateProvider {

    private final BigDecimal exchangeRate;

    public StubExchangeRateProvider(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;

    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        return exchangeRate;
    }
}
