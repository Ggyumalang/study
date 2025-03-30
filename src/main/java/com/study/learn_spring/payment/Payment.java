package com.study.learn_spring.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@ToString
public class Payment {

    private final Long orderId;
    private final String currency;
    private final BigDecimal amount;
    private final BigDecimal exchangeRate;
    private final BigDecimal convertedAmount;
    private final LocalDateTime validUntil;
}
