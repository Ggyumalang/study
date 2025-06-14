package com.study.learn_spring;

import com.study.learn_spring.exchangerate.CachedExchangeRateProvider;
import com.study.learn_spring.exchangerate.RestTemplateExchangeRateProvider;
import com.study.learn_spring.exchangerate.cache.ExchangeRateCacheProvider;
import com.study.learn_spring.exchangerate.cache.InMemoryExchangeRateCacheProvider;
import com.study.learn_spring.exchangerate.template.ExchangeRateApiTemplate;
import com.study.learn_spring.payment.ExchangeRateProvider;
import com.study.learn_spring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExchangeRateProvider(), clock());
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
//        return new SimpleExchangeRateProvider();
//        return new HttpApiExchangeRateProvider(exchangeRateApiTemplate());
        return new RestTemplateExchangeRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public ExchangeRateApiTemplate exchangeRateApiTemplate() {
        return new ExchangeRateApiTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
