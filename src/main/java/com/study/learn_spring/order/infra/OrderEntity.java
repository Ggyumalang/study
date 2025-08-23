package com.study.learn_spring.order.infra;

import com.study.learn_spring.order.application.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

//orm.xml이 어노테이션 설정을 overriding 한다.
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String no;

    private BigDecimal total;

    public OrderEntity(Long id, String no, BigDecimal total) {
        this.id = id;
        this.no = no;
        this.total = total;
    }

    //스태틱 메서드
    public static OrderEntity fromDomain(Order order) {
        return new OrderEntity(order.getId(), order.getNo(), order.getTotal());
    }

    //인스턴스 메서드
    public Order toDomain() {
        return new Order(getId(), getNo(), getTotal());
    }
}
