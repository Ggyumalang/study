package com.study.learn_spring;

import java.math.BigDecimal;

//관계 설정 책임의 분리
public class Client {
    public static void main(String[] args) {
        // 객체 설정 및 관계 설정
        ObjectFactory objectFactory = new ObjectFactory();
        PaymentService paymentService = objectFactory.paymentService();

        //객체 사용
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment = " + payment);
    }
}
