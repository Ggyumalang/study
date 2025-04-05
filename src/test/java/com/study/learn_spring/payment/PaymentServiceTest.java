package com.study.learn_spring.payment;

import com.study.learn_spring.exchangerate.HttpApiExchangeRateProvider;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    void prepare() {
        //given - 수행 준비
        ExchangeRateProvider exchangeRateProvider = new HttpApiExchangeRateProvider();
        PaymentService paymentService = new PaymentService(exchangeRateProvider);

        //when - 실제 기능 수행
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));

        //then - 수행 결과 검증
        //환율 확인
        BigDecimal exchangeRate = payment.getExchangeRate();
        assertThat(exchangeRate).isGreaterThan(BigDecimal.ZERO);
        //원화 환산 금액
        BigDecimal convertedAmount = payment.getConvertedAmount();
        assertThat(convertedAmount).isEqualTo(payment.getAmount().multiply(exchangeRate));
        //유효 기간
        LocalDateTime validUntil = payment.getValidUntil();
        // 마이크로 초 차이로 실패
//        assertThat(validUntil).isEqualTo(LocalDateTime.now().plusMinutes(30L));
        assertThat(validUntil).isAfter(LocalDateTime.now()).isBefore(LocalDateTime.now().plusMinutes(30L));
    }
}