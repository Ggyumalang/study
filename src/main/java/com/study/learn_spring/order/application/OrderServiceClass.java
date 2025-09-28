package com.study.learn_spring.order.application;

import com.study.learn_spring.order.infra.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderServiceClass {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager txManager;

    //Portable Service Abstraction
    @Transactional //PSA + AOP
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
//        return new TransactionTemplate(txManager).execute(status -> orderRepository.save(order));
        return orderRepository.save(order);
    }

    public List<Order> createOrders(List<OrderRequest> requests) {
        return new TransactionTemplate(txManager).execute(status ->
                requests.stream()
                        .map(request -> createOrder(request.orderNo(), request.total()))
                        .toList()
        );
    }

    public Order findByOrderNo(String orderNo) {
        return orderRepository.findByOrderNo(orderNo)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
    }
}
