package com.study.learn_spring;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CachedExchangeRateProvider implements ExchangeRateProvider {

    // 요청이 동시다발적으로 오기 때문에 Concurrent 사용하여 동시성 제어 가능
    // 여기는 그냥 해쉬맵 사용
//    private static final Map<String, BigDecimal> CACHED_EXCHANGE_RATES = new ConcurrentHashMap<>();
    private static final Map<String, BigDecimal> CACHED_EXCHANGE_RATES = new HashMap<>();
    // 원본 객체 (HttpApiExchangeRateProvider) 참조하겠다.
    private final ExchangeRateProvider target;

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal exchangeRate = CACHED_EXCHANGE_RATES.get(currency);
        if (exchangeRate == null) {
            exchangeRate = target.getExchangeRate(currency);
            CACHED_EXCHANGE_RATES.put(currency, exchangeRate);
        }
        return exchangeRate;
    }
}
