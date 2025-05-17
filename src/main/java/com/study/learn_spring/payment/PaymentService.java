package com.study.learn_spring.payment;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Clock;

@RequiredArgsConstructor
public class PaymentService {
    private final ExchangeRateProvider exchangeRateProvider;
    private final Clock clock;

    public Payment prepare(Long orderId, String currency, BigDecimal amount) {
        //환율 조회
        try {
            //환율 조회
            BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency);
            return Payment.createPrepared(orderId, currency, amount, exchangeRate, clock);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
