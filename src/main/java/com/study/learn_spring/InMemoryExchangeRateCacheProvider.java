package com.study.learn_spring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryExchangeRateCacheProvider implements ExchangeRateCacheProvider {

    private static final Map<String, BigDecimal> CACHED_EXCHANGE_RATES = new HashMap<>();
    private static final Map<String, LocalDateTime> CACHED_EXPIRY_TIMES = new HashMap<>();

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal exchangeRate = getCachedExchangeRate(currency);
        if (isCachedDataEmpty(exchangeRate)) {
            return null;
        }

        if (isCachedDataExpired(currency)) {
            return null;
        }

        return exchangeRate;
    }

    private static boolean isCachedDataEmpty(BigDecimal exchangeRate) {
        return exchangeRate == null;
    }

    @Override
    public void setExchangeRate(String currency, BigDecimal exchangeRate, LocalDateTime expiryTime) {
        CACHED_EXCHANGE_RATES.put(currency, exchangeRate);
        CACHED_EXPIRY_TIMES.put(currency, expiryTime);
    }

    private static BigDecimal getCachedExchangeRate(String currency) {
        return CACHED_EXCHANGE_RATES.get(currency);
    }

    private static boolean isCachedDataExpired(String currency) {
        LocalDateTime expiryTime = CACHED_EXPIRY_TIMES.get(currency);
        return expiryTime == null || LocalDateTime.now().isAfter(expiryTime);
    }
}
