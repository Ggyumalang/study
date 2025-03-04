package com.study.learn_spring;

import java.util.Arrays;

public class GenericTest {
    public static void main(String[] args) {
        String[] attributes = pickTwo("hi", "iam", "test");
        System.out.println(Arrays.toString(attributes));
    }

    private static <T> T[] pickTwo(T a, T b, T c) {
        System.out.println((Arrays.toString(toArray(a, b))));
        return toArray(a, b);
    }

    private static <T> T[] toArray(T... args) {
        return args;
    }
}
