package com.study.learn_spring.order.application;

import com.study.learn_spring.OrderConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {
        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        assertThat(order.getId()).isPositive();
    }
}