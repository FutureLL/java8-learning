package com.lilei.java8.Collector;

import com.lilei.java8.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: Collector Introduce [聚合]
 * @author: Mr.Li
 * @date: Created in 2020/1/8 12:22
 * @version: 1.0
 * @modified By:
 * <p>
 * Collectors:
 * Implementations of Collector that implement various useful reduction operations,
 * such as accumulating elements into collections, summarizing elements according to various criteria, etc.
 * <p>
 * Collector:
 * A mutable reduction operation that accumulates input elements into a mutable result container,
 * optionally transforming the accumulated result into a final representation after all input elements have been processed.
 * Reduction operations can be performed either sequentially or in parallel.
 */
public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        // 实现颜色为green的Apple对象的List集合
        List<Apple> greenList = list.stream()
                .filter(apple -> apple.getColor().equals("green"))
                // toList(): Returns a Collector that accumulates the input elements into a new List.
                .collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        // 原先的方式: 按照颜色输出它的map集合
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);

        // Stream的方式
        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);

        // Collector的方式
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);
    }

    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();

        for (Apple apple : apples) {
            List<Apple> list = map.get(apple.getColor());

            if (list == null) {
                list = new ArrayList<>();
                map.put(apple.getColor(), list);
            }
            list.add(apple);
        }
        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();

        apples.stream().forEach(apple -> {
            List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(apple.getColor(), list);
                return list;
            });
            colorList.add(apple);
        });

        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        /**
         * groupingBy(Function<? super T,? extends K> classifier):
         *      Returns a Collector implementing a "group by" operation on input elements of type T,
         *      grouping elements according to a classification function, and returning the results in a Map.
         */
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
