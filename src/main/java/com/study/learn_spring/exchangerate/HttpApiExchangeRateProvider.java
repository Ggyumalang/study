package com.study.learn_spring.exchangerate;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        System.out.println(" >>> HttpApiExchangeRateProvider 호출");
        URI uri = new URI("https://open.er-api.com/v6/latest/" + currency);
        URLConnection urlConnection = uri.toURL().openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String body = br.lines().collect(Collectors.joining());

        //문자열 (바이트배열) -> 객체로 변환 -> 디시리얼라이즈 (디시리얼라이저)
        //객체 -> 문자열로 변환 -> 시리얼라이즈 (시리얼라이저)
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData exchangeRateData = mapper.readValue(body, ExchangeRateData.class);
        return exchangeRateData.rates().get("KRW");
    }
}
