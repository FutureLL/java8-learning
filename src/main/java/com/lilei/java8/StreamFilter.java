package com.lilei.java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @description: Stream Filter
 * @author: Mr.Li
 * @date: Created in 2020/1/6 13:47
 * @version: 1.0
 * @modified By:
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);

        List<Integer> result = list.stream().filter(index -> index % 2 == 0).collect(toList());
        System.out.println(result);

        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        result = list.stream().distinct().collect(toList());
        System.out.println(result);

        result = list.stream().skip(5).collect(toList());
        System.out.println(result);

        result = list.stream().limit(5).collect(toList());
        System.out.println(result);
    }
}
