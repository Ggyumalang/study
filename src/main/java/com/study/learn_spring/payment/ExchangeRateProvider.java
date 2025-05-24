package com.study.learn_spring.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {
    BigDecimal getExchangeRate(String currency);
}
