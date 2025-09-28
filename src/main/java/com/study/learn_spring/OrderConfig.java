package com.study.learn_spring;

import com.study.learn_spring.order.application.OrderRepository;
import com.study.learn_spring.order.application.OrderServiceClass;
import com.study.learn_spring.order.infra.OrderJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class OrderConfig {

    @Bean
//    public OrderRepository orderRepository() {
//        return new OrderJpaRepository();
//    }

    public OrderRepository orderRepository(DataSource dataSource) {
        return new OrderJdbcRepository(dataSource);
    }

    @Bean
    //Spring IoC/DI Container 가 스프링 빈 주입
//    public OrderService orderService() {
//        return new OrderServiceImpl(orderRepository());
//    }
    public OrderServiceClass orderService(PlatformTransactionManager txManager, OrderRepository orderRepository) {
        return new OrderServiceClass(orderRepository, txManager);
    }
}
