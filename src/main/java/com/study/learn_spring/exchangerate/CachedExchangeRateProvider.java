package com.study.learn_spring.exchangerate;

import com.study.learn_spring.exchangerate.cache.ExchangeRateCacheProvider;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CachedExchangeRateProvider implements ExchangeRateProvider {

    // 요청이 동시다발적으로 오기 때문에 Concurrent 사용하여 동시성 제어 가능
    // 여기는 그냥 해쉬맵 사용
    // private static final Map<String, BigDecimal> CACHED_EXCHANGE_RATES = new ConcurrentHashMap<>();
    private final ExchangeRateProvider target;
    private final ExchangeRateCacheProvider cacheProvider;

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal cachedExchangeRate = cacheProvider.getExchangeRate(currency);
        if (isCachedDataEmpty(cachedExchangeRate)) {
            return getNewExchangeRate(currency);
        }
        return cachedExchangeRate;
    }

    private static boolean isCachedDataEmpty(BigDecimal exchangeRate) {
        return exchangeRate == null;
    }

    private BigDecimal getNewExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal exchangeRate = target.getExchangeRate(currency);
        cacheProvider.setExchangeRate(currency, exchangeRate, LocalDateTime.now().plusSeconds(3));
        return exchangeRate;
    }
}
