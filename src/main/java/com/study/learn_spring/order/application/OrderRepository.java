package com.study.learn_spring.order.application;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByOrderNo(String orderNo);
}
