package com.study.learn_spring.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentTest {

    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        BigDecimal amount = BigDecimal.valueOf(50.7);
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        Payment payment = Payment.createPrepared(100L, "USD", amount, bigDecimal, clock);

        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(amount.multiply(payment.getExchangeRate()));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30L));
    }
}