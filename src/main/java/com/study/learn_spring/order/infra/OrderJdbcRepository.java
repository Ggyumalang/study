package com.study.learn_spring.order.infra;

import com.study.learn_spring.order.application.Order;
import com.study.learn_spring.order.application.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.util.Optional;

public class OrderJdbcRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public OrderJdbcRepository(DataSource datasource) {
        this.jdbcClient = JdbcClient.create(datasource);
    }

    @PostConstruct
    void initDb() {
        // """ -> 텍스트 블록 (자바15부터 지원)
        String sql = """
                    CREATE TABLE orders (id BIGINT NOT NULL, no VARCHAR(255), total NUMERIC(38,2), PRIMARY KEY (id));
                    ALTER TABLE IF EXISTS orders DROP CONSTRAINT IF EXISTS UK43egxxciqr9ncgmxbdx2avi8n;
                    ALTER TABLE IF EXISTS orders ADD CONSTRAINT UK43egxxciqr9ncgmxbdx2avi8n UNIQUE (NO);
                    CREATE SEQUENCE orders_SEQ START WITH 1 INCREMENT BY 50
                """;
        jdbcClient.sql(sql).update();
    }

    @Override
    public Order save(Order order) {
        String sql = "SELECT NEXT VALUE FOR orders_SEQ";
        Long id = jdbcClient.sql(sql)
                .query(Long.class)
                .single();

        sql = "INSERT INTO orders (id, no, total) VALUES (:id, :no, :total)";
        jdbcClient.sql(sql)
                .param("id", id)
                .param("no", order.getNo())
                .param("total", order.getTotal())
                .update();

        return order.with(id);
    }

    @Override
    public Optional<Order> findByOrderNo(String orderNo) {
        String sql = "SELECT id, no, total FROM orders WHERE no = :orderNo";
        return jdbcClient.sql(sql)
                .param("orderNo", orderNo)
                .query(Order.class)
                .optional();
    }
}
