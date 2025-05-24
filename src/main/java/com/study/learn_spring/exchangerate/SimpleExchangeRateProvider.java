package com.study.learn_spring.exchangerate;

import com.study.learn_spring.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {
    @Override
    public BigDecimal getExchangeRate(String currency) {
        return BigDecimal.valueOf(1000);
    }
}
