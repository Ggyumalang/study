package com.study.learn_spring.exchangerate.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.learn_spring.exchangerate.ExchangeRateData;

import java.math.BigDecimal;

public class ErApiExchangeRateExtractor implements ExchangeRateExtractor {
    @Override
    public BigDecimal extract(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateData exchangeRateData = mapper.readValue(body, ExchangeRateData.class);
        return exchangeRateData.rates().get("KRW");
    }
}
