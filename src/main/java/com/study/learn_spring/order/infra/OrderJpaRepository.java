package com.study.learn_spring.order.infra;

import com.study.learn_spring.order.application.Order;
import com.study.learn_spring.order.application.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public class OrderJpaRepository implements OrderRepository {

    //EntityManagerFactory : Thread-safe O -> 스프링 빈으로 등록하기 적합
    //EntityManager : Thread-safe X -> 스프링 빈으로 등록하기 부적합
    @PersistenceContext
    private EntityManager em;

    @Override
    public Order save(Order order) {
        // order -> orderEntity 객체 변환
        OrderEntity orderEntity = OrderEntity.fromDomain(order);
        em.persist(orderEntity);
        //em.flush(); //JPA: 쓰기 지연(Write-behind) CUD

        // orderEntity -> order 객체 변환
        return orderEntity.toDomain();
    }

    @Override
    public Optional<Order> findByOrderNo(String orderNo) {
        return Optional.empty();
    }
}
