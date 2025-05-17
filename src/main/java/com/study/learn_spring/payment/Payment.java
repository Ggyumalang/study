package com.study.learn_spring.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Clock;
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

    // 정적 팩토리 메서드 패턴
    // 메서드 명을 지정할 수 있음.
    public static Payment createPrepared(
            Long orderId, String currency, BigDecimal amount, BigDecimal exchangeRate, Clock clock
    ) {
        //금액 계산
        BigDecimal convertedAmount = exchangeRate.multiply(amount);

        //유효시간 계산 - 현재 시간으로부터 30분
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, amount, exchangeRate, convertedAmount, validUntil);
    }
}
