package com.study.learn_spring.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        Sort sort = new Sort();
        System.out.println(">>> sort = " + sort);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList("yyyy", "gg", "mmm", "l"),
                        new String[]{"l", "gg", "mmm", "yyyy"}),
                Arguments.of(Arrays.asList("yyyy", "gg", "mmm", "l"),
                        new String[]{"l", "gg", "mmm", "yyyy"}),
                Arguments.of(Arrays.asList("yyyy", "gg", "mmm", "l"),
                        new String[]{"l", "gg", "mmm", "yyyy"})
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void sortByLength(List<String> source, String[] expected) {
        List<String> result = sort.sortByLength(source);

        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("기본 문자열 정렬 확인")
    void sortByLength2(List<String> source, String[] expected) {
        List<String> list = Arrays.asList("yyyy", "gg", "mmm", "l");
        List<String> result = sort.sortByLength(list);

        assertThat(result).containsExactly("l", "gg", "mmm", "yyyy");
    }

    @Test
    @DisplayName("문자열 길이가 동일하면 입력한 문자 순으로 정렬한다.")
    void sortByLength3() {
        List<String> list = Arrays.asList("aaaa", "ddd", "bbb", "ccc");
        List<String> result = sort.sortByLength(list);

        assertThat(result).containsExactly("ddd", "bbb", "ccc", "aaaa");
    }
}