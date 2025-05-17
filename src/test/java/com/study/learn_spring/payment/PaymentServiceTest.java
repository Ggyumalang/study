package com.study.learn_spring.payment;

import com.study.learn_spring.exchangerate.StubExchangeRateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

// 수동 DI 테스트
class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void beforeEach() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void prepare() {
        //given - 수행 준비
        BigDecimal exchangeRateVal = BigDecimal.valueOf(1000);
        ExchangeRateProvider exchangeRateProvider = new StubExchangeRateProvider(exchangeRateVal);

        PaymentService paymentService = new PaymentService(exchangeRateProvider, clock);
        BigDecimal amount = BigDecimal.valueOf(50.7);

        //when - 실제 기능 수행
        Payment payment = paymentService.prepare(100L, "USD", amount);

        //then - 수행 결과 검증
        //환율 확인
        BigDecimal exchangeRate = payment.getExchangeRate();
        assertThat(exchangeRate).isEqualByComparingTo(exchangeRateVal);

        //통화량 확인 (선택적 검증)
        assertThat(payment.getAmount()).isEqualByComparingTo(amount);

        //원화 환산 금액
        BigDecimal convertedAmount = payment.getConvertedAmount();
        //실제 BigDecimal 값을 확인하는 것이기에 isEqualByComparingTo
        assertThat(convertedAmount).isEqualByComparingTo(amount.multiply(exchangeRate));

        //유효 기간
        LocalDateTime validUntil = payment.getValidUntil();

        // 마이크로 초 차이로 실패
        assertThat(validUntil).isEqualTo(LocalDateTime.now(clock).plusMinutes(30L));
//        assertThat(validUntil).isAfter(LocalDateTime.now()).isBefore(LocalDateTime.now());
    }

    @Test
    @DisplayName("valid Until Check")
    void validUntil() {
        //given - 수행 준비
        BigDecimal exchangeRateVal = BigDecimal.valueOf(1000);
        ExchangeRateProvider exchangeRateProvider = new StubExchangeRateProvider(exchangeRateVal);
        PaymentService paymentService = new PaymentService(exchangeRateProvider, clock);
        BigDecimal amount = BigDecimal.valueOf(50.7);

        //when - 실제 기능 수행
        Payment payment = paymentService.prepare(100L, "USD", amount);
        //유효 기간
        LocalDateTime validUntil = payment.getValidUntil();

        // 마이크로 초 차이로 실패
        assertThat(validUntil).isEqualTo(LocalDateTime.now(clock).plusMinutes(30L));
    }
}