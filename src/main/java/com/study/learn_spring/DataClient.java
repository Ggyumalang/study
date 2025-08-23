package com.study.learn_spring;

import com.study.learn_spring.order.application.Order;
import com.study.learn_spring.order.application.OrderRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = applicationContext.getBean(OrderRepository.class);
//        OrderJpaRepository repository = applicationContext.getBean(OrderJpaRepository.class);
        JpaTransactionManager txManager = applicationContext.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(txManager).execute(status -> {
                Order order1 = new Order("100", BigDecimal.TEN);
                repository.save(order1);
                System.out.println(">>> order1=" + order1);

                Order order2 = new Order("100", BigDecimal.TEN);
                repository.save(order2);
                System.out.println(">>> order2=" + order2);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            System.out.println(">>> 주문번호 중복 복구 처리...");
        }
    }
}
