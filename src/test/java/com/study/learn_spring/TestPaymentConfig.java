package com.study.learn_spring;

import com.study.learn_spring.exchangerate.StubExchangeRateProvider;
import com.study.learn_spring.payment.ExchangeRateProvider;
import com.study.learn_spring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration // @Component + Proxy 기능으로 빈 객체를 싱글톤으로 보장
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new StubExchangeRateProvider(BigDecimal.valueOf(1000));
    }
}
