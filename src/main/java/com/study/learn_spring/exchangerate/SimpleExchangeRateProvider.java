package com.study.learn_spring.exchangerate;

import com.study.learn_spring.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {
    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return BigDecimal.valueOf(1000);
    }
}
