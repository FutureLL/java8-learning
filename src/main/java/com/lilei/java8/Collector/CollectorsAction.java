package com.lilei.java8.Collector;

import com.lilei.java8.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: Collectors Action
 * @author: Mr.Li
 * @date: Created in 2020/1/8 14:16
 * @version: 1.0
 * @modified By:
 */
public class CollectorsAction {

    private final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();

        testCollectingAndThen();

        testCounting();

        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
    }

    private static void testAveragingDouble() {
        System.out.print("testAveragingDouble ---> ");
        /**
         * averagingDouble(ToDoubleFunction<? super T> mapper):
         * Returns a Collector that produces the arithmetic mean of a double-valued function applied to the input elements.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingInt() {
        System.out.print("testAveragingInt ---> ");
        /**
         * averagingLong(ToLongFunction<? super T> mapper):
         * Returns a Collector that produces the arithmetic mean of a long-valued function applied to the input elements.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testAveragingLong() {
        System.out.print("testAveragingLong ---> ");
        /**
         * averagingInt(ToIntFunction<? super T> mapper):
         * Returns a Collector that produces the arithmetic mean of an integer-valued function applied to the input elements.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    private static void testCollectingAndThen() {
        System.out.print("testCollectingAndThen ---> ");
        /**
         * collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher):
         * Adapts a Collector to perform an additional finishing transformation.
         *
         * 将long类型变成String
         */
        Optional.ofNullable(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingLong(Dish::getCalories), a -> "Average Calories is: " + a)))
                .ifPresent(System.out::println);

        List<Dish> list = menu.stream().filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                // unmodifiableList(): Returns an unmodifiable view of the specified list.
                // 保证这个list在后续的程序中没有被更改,如果有的话,那么会报错: UnsupportedOperationException
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        // list.add(new Dish("", false, 100, Dish.Type.OTHER));
        System.out.println(list);
    }

    private static void testCounting() {
        System.out.print("testCounting ---> ");
        /**
         * counting():
         * Returns a Collector accepting elements of type T that counts the number of input elements.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    private static void testGroupingByFunction() {
        System.out.print("testGroupingByFunction ---> ");
        /**
         * groupingBy(Function<? super T,? extends K> classifier):
         *   Returns a Collector implementing a "group by" operation on input elements of type T,
         *   grouping elements according to a classification function, and returning the results in a Map.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    private static void testGroupingByFunctionAndCollector() {
        System.out.print("testGroupingByFunction ---> ");
        /**
         * groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream):
         *   Returns a Collector implementing a cascaded "group by" operation on input elements of type T,
         *   grouping elements according to a classification function, and then performing a reduction operation
         *   on the values associated with a given key using the specified downstream Collector.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.print("testGroupingByFunctionAndSupplierAndCollector ---> ");
        /**
         * groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream):
         *   Returns a Collector implementing a cascaded "group by" operation on input elements of type T,
         *   grouping elements according to a classification function, and then performing a reduction operation
         *   on the values associated with a given key using the specified downstream Collector.
         */
        TreeMap<Dish.Type, Long> map = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.counting()));

        Optional.ofNullable(map).ifPresent(System.out::println);
    }
}
