package com.study.learn_spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal amount) {
        //환율 조회
        URI uri;
        try {
            uri = new URI("https://open.er-api.com/v6/latest/" + currency);
            URLConnection urlConnection = uri.toURL().openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String body = br.lines().collect(Collectors.joining());

            ObjectMapper mapper = new ObjectMapper();
            ExchangeRateDate exchangeRateDate = mapper.readValue(body, ExchangeRateDate.class);
            BigDecimal exchangeRate = exchangeRateDate.rates().get("KRW");

            //금액 계산
            BigDecimal convertedAmount = exchangeRate.multiply(amount);

            //유효시간 계산 - 현재 시간으로부터 30분
            LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

            return new Payment(orderId, currency, amount, exchangeRate, convertedAmount, validUntil);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        //금액 계산

        //유효시간 개선

    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment = " + payment);
    }
}
