package com.study.learn_spring;

import com.study.learn_spring.order.application.OrderRepository;
import com.study.learn_spring.order.application.OrderService;
import com.study.learn_spring.order.infra.OrderJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(DataConfig.class)
// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository() {
        return new OrderJpaRepository();
    }

    @Bean
    //Spring IoC/DI Container 가 스프링 빈 주입
    public OrderService orderService(PlatformTransactionManager txManager) {
        return new OrderService(orderRepository(), txManager);
    }
}
