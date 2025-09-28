package com.study.learn_spring.order.application;

import com.study.learn_spring.order.infra.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderServiceTxProxy implements OrderService {

    private final OrderService target;
    private final PlatformTransactionManager txManager;

    // @Transactional //PSA + AOP
    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(txManager).execute(status -> target.createOrder(no, total));
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> requests) {
        return new TransactionTemplate(txManager).execute(status -> target.createOrders(requests));
    }
}
