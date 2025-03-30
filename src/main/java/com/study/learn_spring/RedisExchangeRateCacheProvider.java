package com.study.learn_spring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Duration;

@RequiredArgsConstructor
public class RedisExchangeRateCacheProvider implements ExchangeRateCacheProvider {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        Object result = redisTemplate.opsForValue().get(currency);
        if (result != null) {
            return BigDecimal.valueOf((double) result);
        }
        return null;
    }

    @Override
    public void setExchangeRate(String currency, BigDecimal exchangeRate, Duration expiryTime) {
        redisTemplate.opsForValue().set(currency, exchangeRate, expiryTime);
    }
}
