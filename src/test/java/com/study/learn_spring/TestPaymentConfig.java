package com.study.learn_spring;

import com.study.learn_spring.exchangerate.StubExchangeRateProvider;
import com.study.learn_spring.payment.ExchangeRateProvider;
import com.study.learn_spring.payment.PaymentService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration // @Component + Proxy 기능으로 빈 객체를 싱글톤으로 보장
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider(), clock());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new StubExchangeRateProvider(BigDecimal.valueOf(1000));
    }

    public Clock clock;

    @Bean
    public Clock clock() {
        if (clock != null) {
            return clock;
        }

        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
