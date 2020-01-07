package com.lilei.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2020/1/6 15:08
 * @version: 1.0
 * @modified By:
 */
public class StreamFind {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional1 = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional1.get());

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional2 = stream.filter(i -> i % 2 == 0).findFirst();
        System.out.println(optional2.get());
    }
}
