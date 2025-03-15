package com.study.learn_spring;

import java.math.BigDecimal;

//관계 설정 책임의 분리
public class Client {
    public static void main(String[] args) {
        ExchangeRateProvider exchangeRateProvider1 = new HttpApiExchangeRateProvider();
        PaymentService paymentService = new PaymentService(exchangeRateProvider1);
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment = " + payment);
    }
}
