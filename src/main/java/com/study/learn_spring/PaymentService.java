package com.study.learn_spring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public abstract class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal amount) {
        //환율 조회
        try {
            //환율 조회
            BigDecimal exchangeRate = getExchangeRate(currency);

            //금액 계산
            BigDecimal convertedAmount = exchangeRate.multiply(amount);

            //유효시간 계산 - 현재 시간으로부터 30분
            LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

            return new Payment(orderId, currency, amount, exchangeRate, convertedAmount, validUntil);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;

    public static void main(String[] args) {
        PaymentService paymentService = new HttpApiPaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment = " + payment);
    }
}
