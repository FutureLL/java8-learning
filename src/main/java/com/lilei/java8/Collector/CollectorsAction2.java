package com.lilei.java8.Collector;

import com.lilei.java8.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @description: Collectors Action
 * @author: Mr.Li
 * @date: Created in 2020/1/8 17:47
 * @version: 1.0
 * @modified By:
 */
public class CollectorsAction2 {

    public final static List<Dish> menu = Arrays.asList(
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
        testGroupingByConcurrent();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();

        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();

        testMapping();

        testMaxBy();
        testMinBy();
    }

    private static void testGroupingByConcurrent() {
        System.out.print("testGroupingByConcurrent ---> ");
        /**
         * groupingByConcurrent(Function<? super T,? extends K> classifier):
         *   Returns a concurrent Collector implementing a "group by" operation on input elements of type T,
         *   grouping elements according to a classification function.
         */
        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.print("testGroupingByConcurrentWithFunctionAndCollector ---> ");
        /**
         * groupingByConcurrent(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream):
         *   Returns a concurrent Collector implementing a cascaded "group by" operation on input elements of type T,
         *   grouping elements according to a classification function, and then performing a reduction operation
         *   on the values associated with a given key using the specified downstream Collector.
         */
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.print("testGroupingByConcurrentWithFunctionAndCollector ---> ");
        /**
         * groupingByConcurrent(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream):
         *   Returns a concurrent Collector implementing a cascaded "group by" operation on input elements of type T,
         *   grouping elements according to a classification function, and then performing a reduction operation
         *   on the values associated with a given key using the specified downstream Collector.
         */
        ConcurrentSkipListMap<Dish.Type, Long> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.counting()));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoining() {
        System.out.print("testJoining ---> ");
        /**
         * joining():
         * Returns a Collector that concatenates the input elements into a String, in encounter order.
         */
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining()))
                .ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiter() {
        System.out.print("testJoiningWithDelimiter ---> ");
        /**
         * joining(CharSequence delimiter):
         * Returns a Collector that concatenates the input elements, separated by the specified delimiter, in encounter order.
         */
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(", ")))
                .ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.print("testJoiningWithDelimiter ---> ");
        /**
         * joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix):
         *   Returns a Collector that concatenates the input elements, separated by the specified delimiter,
         *   with the specified prefix and suffix, in encounter order.
         */
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining("#", "start< ", " >end")))
                .ifPresent(System.out::println);
    }

    private static void testMapping() {
        System.out.print("testMapping ---> ");
        /**
         * mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream):
         *   Adapts a Collector accepting elements of type U to one accepting elements of type T by applying a mapping
         *   function to each input element before accumulation.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining("#"))))
                .ifPresent(System.out::println);
    }

    private static void testMaxBy() {
        System.out.print("testMaxBy ---> ");
        /**
         * maxBy(Comparator<? super T> comparator):
         * Returns a Collector that produces the maximal element according to a given Comparator, described as an Optional<T>.
         *
         * Comparator:
         *   A comparison function, which imposes a total ordering on some collection of objects.
         *
         * comparingInt(ToIntFunction<? super T> keyExtractor):
         *   Accepts a function that extracts an int sort key from a type T,
         *   and returns a Comparator<T> that compares by that sort key.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testMinBy() {
        System.out.print("testMinBy ---> ");
        /**
         * minBy(Comparator<? super T> comparator):
         * Returns a Collector that produces the minimal element according to a given Comparator, described as an Optional<T>.
         *
         * Comparator:
         *   A comparison function, which imposes a total ordering on some collection of objects.
         *
         * comparingInt(ToIntFunction<? super T> keyExtractor):
         *   Accepts a function that extracts an int sort key from a type T,
         *   and returns a Comparator<T> that compares by that sort key.
         */
        Optional.ofNullable(menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }
}
