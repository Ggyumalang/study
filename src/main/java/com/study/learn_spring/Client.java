package com.study.learn_spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

//관계 설정 책임의 분리
public class Client {

    public static void main(String[] args) throws InterruptedException {
        // 객체 설정 및 관계 설정
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
//
//        ObjectFactory objectFactory = new ObjectFactory();
//        PaymentService paymentService = objectFactory.paymentService();

        //객체 사용
        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment1 = " + payment1);

        //객체 사용
        Payment payment2 = paymentService.prepare(200L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment2 = " + payment2);

        //객체 사용
        Thread.sleep(3000);
        Payment payment3 = paymentService.prepare(300L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment3 = " + payment3);
    }
}
