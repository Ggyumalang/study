package com.study.learn_spring;

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
        return new CachedExchangeRateProvider(exchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new HttpApiExchangeRateProvider();
    }
}
