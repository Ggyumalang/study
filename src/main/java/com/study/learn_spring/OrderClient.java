package com.study.learn_spring;

import com.study.learn_spring.order.application.Order;
import com.study.learn_spring.order.application.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

//관계 설정 책임의 분리
public class OrderClient {

    public static void main(String[] args) throws InterruptedException {
        // 객체 설정 및 관계 설정
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = applicationContext.getBean(OrderService.class);

        Order order = service.createOrder("0100", BigDecimal.TEN);
        System.out.println(">>> order: " + order);
    }
}
