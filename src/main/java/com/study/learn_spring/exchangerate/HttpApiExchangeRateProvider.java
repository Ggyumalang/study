package com.study.learn_spring.exchangerate;

import com.study.learn_spring.exchangerate.template.ExchangeRateApiTemplate;
import com.study.learn_spring.payment.ExchangeRateProvider;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HttpApiExchangeRateProvider implements ExchangeRateProvider {

    private final ExchangeRateApiTemplate exchangeRateApiTemplate;

    @Override
    public BigDecimal getExchangeRate(String currency) {
        System.out.println(" >>> HttpApiExchangeRateProvider 호출");
        String url = "https://open.er-api.com/v6/latest/" + currency;

//        return new ExchangeRateApiTemplate().getExchangeRate(url, new LegacyApiExecutor(), new ErApiExchangeRateExtractor());
        return exchangeRateApiTemplate.getExchangeRate(url);
    }
}
