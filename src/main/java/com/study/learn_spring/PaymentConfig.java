package com.study.learn_spring;

import com.study.learn_spring.exchangerate.CachedExchangeRateProvider;
import com.study.learn_spring.exchangerate.ExchangeRateProvider;
import com.study.learn_spring.exchangerate.HttpApiExchangeRateProvider;
import com.study.learn_spring.exchangerate.cache.ExchangeRateCacheProvider;
import com.study.learn_spring.exchangerate.cache.InMemoryExchangeRateCacheProvider;
import com.study.learn_spring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider cachedExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider(), exchangeRateCacheProvider());
    }

    @Bean
    public ExchangeRateCacheProvider exchangeRateCacheProvider() {
        return new InMemoryExchangeRateCacheProvider();
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new HttpApiExchangeRateProvider();
    }
}
