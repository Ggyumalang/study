package com.study.learn_spring.exchangerate;

import com.study.learn_spring.payment.ExchangeRateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RestTemplateExchangeRateProvider implements ExchangeRateProvider {

    private final RestTemplate restTemplate;

    @Override
    public BigDecimal getExchangeRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        ExchangeRateData data = restTemplate.getForObject(url, ExchangeRateData.class);
        return data.rates().get("KRW");
    }
}
