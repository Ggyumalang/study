package com.study.learn_spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Strategy 패턴
// : 전체 컨텍스트 중 일부분을 외부(익명함수)에서 주입 받아 변경 하여 수행
// ex) 정렬 진행 중 정렬 기준을 변경한다.
public class Sort {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("b", "dddd", "aaa", "cc");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        list.forEach(System.out::println);
    }
}
