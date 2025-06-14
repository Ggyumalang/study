package com.study.learn_spring.exchangerate.template;

import com.study.learn_spring.exchangerate.apiexecutor.ApiExecutor;
import com.study.learn_spring.exchangerate.apiexecutor.LegacyApiExecutor;
import com.study.learn_spring.exchangerate.extractor.ErApiExchangeRateExtractor;
import com.study.learn_spring.exchangerate.extractor.ExchangeRateExtractor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ExchangeRateApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExchangeRateExtractor exchangeRateExtractor;

    // 기본 생성자 - Default Call back 설정
    public ExchangeRateApiTemplate() {
        this(new LegacyApiExecutor(), new ErApiExchangeRateExtractor());
    }

    public ExchangeRateApiTemplate(ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
        this.apiExecutor = apiExecutor;
        this.exchangeRateExtractor = exchangeRateExtractor;
    }

    public BigDecimal getExchangeRate(
            String url
    ) {
        //1. URI 준비
        URI uri;
        try {
            uri = new URI(url);
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
            return exchangeRateExtractor.extract(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
