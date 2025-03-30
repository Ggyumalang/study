package com.study.learn_spring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Duration;

public interface ExchangeRateCacheProvider {
    BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;

    void setExchangeRate(String currency, BigDecimal exchangeRate, Duration expiryTime);
}
