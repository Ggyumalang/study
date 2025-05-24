package com.study.learn_spring.exchangerate.cache;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExchangeRateCacheProvider {
    BigDecimal getExchangeRate(String currency);

    void setExchangeRate(String currency, BigDecimal exchangeRate, LocalDateTime expiryTime);
}
