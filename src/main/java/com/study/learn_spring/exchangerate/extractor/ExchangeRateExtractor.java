package com.study.learn_spring.exchangerate.extractor;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateExtractor {
    BigDecimal extract(String body) throws IOException;
}
