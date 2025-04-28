package com.study.learn_spring.study;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

// 학습 테스트
class ClockTest {

    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        System.out.println(">>> dt1=" + dt1);
        System.out.println(">>> dt2=" + dt2);
        assertThat(dt1).isEqualTo(dt2);
    }

    @Test
    void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        System.out.println(">>> dt1=" + dt1);
        System.out.println(">>> dt2=" + dt2);
        assertThat(dt1).isEqualTo(dt2);
    }
}