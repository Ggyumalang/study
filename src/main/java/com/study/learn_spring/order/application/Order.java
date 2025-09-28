package com.study.learn_spring.order.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

//orm.xml이 어노테이션 설정을 overriding 한다.
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@Table(name = "orders")
public class Order {

    //    @Id
//    @GeneratedValue
    private Long id;

    //    @Column(unique = true)
    private String no;

    private BigDecimal total;

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Order(Long id, String no, BigDecimal total) {
        this.id = id;
        this.no = no;
        this.total = total;
    }

    public Order with(Long id) {
        return new Order(id, getNo(), getTotal());
    }
}
