package com.study.learn_spring.exchangerate;

import com.study.learn_spring.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class StubExchangeRateProvider implements ExchangeRateProvider {

    private final BigDecimal exchangeRate;

    public StubExchangeRateProvider(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;

    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return exchangeRate;
    }
}
