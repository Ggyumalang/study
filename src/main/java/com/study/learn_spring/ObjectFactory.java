package com.study.learn_spring;

// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class ObjectFactory {

    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider());
    }

    public ExchangeRateProvider exchangeRateProvider() {
        return new HttpApiExchangeRateProvider();
    }
}
