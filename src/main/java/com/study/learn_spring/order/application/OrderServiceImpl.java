package com.study.learn_spring.order.application;

import com.study.learn_spring.order.infra.OrderRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> createOrders(List<OrderRequest> requests) {
        return requests.stream()
                .map(request -> createOrder(request.orderNo(), request.total()))
                .toList();
    }
}
