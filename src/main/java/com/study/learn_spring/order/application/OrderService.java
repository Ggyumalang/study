package com.study.learn_spring.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager txManager;

    //@Transactional //PSA + AOP
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        return new TransactionTemplate(txManager).execute(status -> orderRepository.save(order));
    }
}
