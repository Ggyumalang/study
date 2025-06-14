package com.study.learn_spring.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.learn_spring.exchangerate.apiexecutor.ApiExecutor;
import com.study.learn_spring.exchangerate.apiexecutor.LegacyApiExecutor;
import com.study.learn_spring.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpApiExchangeRateProvider implements ExchangeRateProvider {
    @Override
    public BigDecimal getExchangeRate(String currency) {
        System.out.println(" >>> HttpApiExchangeRateProvider 호출");

        return runApiAndGetExchangeRate(currency, new LegacyApiExecutor());

    }

    private static BigDecimal runApiAndGetExchangeRate(String currency, ApiExecutor apiExecutor) {
        //1. URI 준비
        URI uri;
        try {
            uri = prepareUri(currency);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //2. API 호출 & 응답 저장
        String body;
        try {
            body = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //문자열 (바이트배열) -> 객체로 변환 -> 디시리얼라이즈 (디시리얼라이저)
        //객체 -> 문자열로 변환 -> 시리얼라이즈 (시리얼라이저)
        //3. Json 파싱 & 환율 추출
        try {
            return extractExchangeRate(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static URI prepareUri(String currency) throws URISyntaxException {
        return new URI("https://open.er-api.com/v6/latest/" + currency);
    }

    private static BigDecimal extractExchangeRate(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData exchangeRateData = mapper.readValue(body, ExchangeRateData.class);
        return exchangeRateData.rates().get("KRW");
    }
}
