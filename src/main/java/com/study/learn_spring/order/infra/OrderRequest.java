package com.study.learn_spring.order.infra;

import java.math.BigDecimal;

public record OrderRequest(String orderNo, BigDecimal total) {
}
