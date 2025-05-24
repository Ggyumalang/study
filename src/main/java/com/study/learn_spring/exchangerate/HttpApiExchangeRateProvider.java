package com.study.learn_spring.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.learn_spring.payment.ExchangeRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class HttpApiExchangeRateProvider implements ExchangeRateProvider {
    @Override
    public BigDecimal getExchangeRate(String currency) {
        System.out.println(" >>> HttpApiExchangeRateProvider 호출");

        //1. URI 준비
        URI uri;
        try {
            uri = new URI("https://open.er-api.com/v6/latest/" + currency);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //2. API 호출 & 응답 저장
        String body;
        try {
            URLConnection urlConnection = uri.toURL().openConnection();
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                body = buff.lines().collect(Collectors.joining());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //문자열 (바이트배열) -> 객체로 변환 -> 디시리얼라이즈 (디시리얼라이저)
        //객체 -> 문자열로 변환 -> 시리얼라이즈 (시리얼라이저)
        //3. Json 파싱 & 환율 추출
        try {
            ObjectMapper mapper = new ObjectMapper();
            ExchangeRateData exchangeRateData = mapper.readValue(body, ExchangeRateData.class);
            return exchangeRateData.rates().get("KRW");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
