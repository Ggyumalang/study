package com.study.learn_spring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ExchangeRateProvider {
    BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;
}
