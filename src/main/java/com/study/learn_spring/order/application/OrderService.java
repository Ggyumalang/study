package com.study.learn_spring.order.application;

import com.study.learn_spring.order.infra.OrderRequest;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal total);

    List<Order> createOrders(List<OrderRequest> requests);
}
