package com.study.learn_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
// 객체 생성 기능 클래스 = 객체 생성 + 관계 설정
public class PaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider cachedExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider(), exchangeRateCacheProvider());
    }

    @Bean
    public ExchangeRateCacheProvider exchangeRateCacheProvider() {
//        return new InMemoryExchangeRateCacheProvider();
        return new RedisExchangeRateCacheProvider(redisTemplate());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new HttpApiExchangeRateProvider();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // Lettuce(스프링 부트 디폴트) , Jedis(요즘 잘 안 쓰임),
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
