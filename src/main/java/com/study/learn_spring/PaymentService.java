package com.study.learn_spring;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PaymentService {
    private final ExchangeRateProvider exchangeRateProvider;

    public Payment prepare(Long orderId, String currency, BigDecimal amount) {
        //환율 조회
        try {
            //환율 조회
            BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency);

            //금액 계산
            BigDecimal convertedAmount = exchangeRate.multiply(amount);

            //유효시간 계산 - 현재 시간으로부터 30분
            LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

            return new Payment(orderId, currency, amount, exchangeRate, convertedAmount, validUntil);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
