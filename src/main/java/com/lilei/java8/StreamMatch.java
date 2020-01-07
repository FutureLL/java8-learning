package com.lilei.java8;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @description: Stream Match
 * @author: Mr.Li
 * @date: Created in 2020/1/6 14:48
 * @version: 1.0
 * @modified By:
 */
public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> streamAllMatch = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        boolean matched = streamAllMatch.allMatch(i -> i > 0);
        System.out.println(matched);

        Stream<Integer> streamAnyMatch = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = streamAnyMatch.anyMatch(i -> i > 6);
        System.out.println(matched);

        Stream<Integer> streamNoneMatch = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        matched = streamNoneMatch.noneMatch(i -> i < 0);
        System.out.println(matched);
    }
}
