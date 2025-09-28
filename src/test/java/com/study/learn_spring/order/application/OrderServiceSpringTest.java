package com.study.learn_spring.order.application;

import com.study.learn_spring.OrderConfig;
import com.study.learn_spring.order.infra.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringJUnitConfig(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderServiceClass orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder() {
        Order order = orderService.createOrder("0100", BigDecimal.TEN);
        assertThat(order.getId()).isPositive();
    }

    @Test
    void createOrders() {
        List<OrderRequest> requests = List.of(
                new OrderRequest("0200", BigDecimal.ONE),
                new OrderRequest("0201", BigDecimal.TWO),
                new OrderRequest("0202", BigDecimal.TEN)
        );

        List<Order> orders = orderService.createOrders(requests);

        assertThat(orders).hasSize(requests.size());
        orders.forEach(order -> assertThat(order.getId()).isPositive());
    }

    @Test
    void createOrders_rollback() {
        List<OrderRequest> requests = List.of(
                new OrderRequest("0300", BigDecimal.ONE),
                new OrderRequest("0300", BigDecimal.TWO)
        );

        assertThatThrownBy(() -> orderService.createOrders(requests))
                .isInstanceOf(DuplicateKeyException.class);

        Optional<Order> result = orderRepository.findByOrderNo("0300");
        assertThat(result).isEmpty();
    }

    @Test
    void findByOrderNo() {
        List<OrderRequest> requests = List.of(
                new OrderRequest("0400", BigDecimal.ONE),
                new OrderRequest("0401", BigDecimal.TWO)
        );

        orderService.createOrders(requests);
        Order byOrderNo = orderService.findByOrderNo("0400");

        assertThat(byOrderNo).isNotNull();
        assertThat(byOrderNo.getTotal()).isEqualByComparingTo(BigDecimal.ONE);

    }
}