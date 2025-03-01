package com.study.learn_spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateDate(
        String result
        , Map<String, BigDecimal> rates
) {

}
