package com.lilei.java8.Collector;

import com.lilei.java8.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.lilei.java8.Collector.CollectorsAction.menu;

/**
 * @description: Collectors Action
 * @author: Mr.Li
 * @date: Created in 2020/1/9 12:32
 * @version: 1.0
 * @modified By:
 */

public class CollectorsAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();

        testReducingWithBinaryOperator();
        testReducingWithIdentityAndBinaryOperator();
        testReducingWithIdentityAndFunctionAndBinaryOperator();

        testSummingDouble();
        testSummingInt();
        testSummingLong();

        testToCollection();
        testToConcurrentMapWithFunction();
        testToConcurrentMapWithFunctionAndBinaryOperator();
        testToConcurrentMapWithFunctionAndBinaryOperatorAndSupplier();
        testToList();
        testToMapFunction();
        testToMapFunctionAndBinaryOperator();
        testToMapFunctionAndBinaryOperatorAndSupplier();
        testToSet();

        testToMapWithSaft();
    }

    private static void testPartitioningByWithPredicate() {
        System.out.print("testPartitioningByWithPredicate ---> ");
        /**
         * partitioningBy(Predicate<? super T> predicate):
         * Returns a Collector which partitions the input elements according to a Predicate, and organizes them into a Map<Boolean, List<T>>.
         */
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetartion));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.print("testPartitioningByWithPredicateAndCollector ---> ");
        /**
         * partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream):
         *   Returns a Collector which partitions the input elements according to a Predicate,
         *   reduces the values in each partition according to another Collector, and organizes them into a Map<Boolean, D>
         *   whose values are the result of the downstream reduction.
         */
        Map<Boolean, Double> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetartion, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testReducingWithBinaryOperator() {
        System.out.print("testReducingWithBinaryOperator ---> ");
        /**
         * reducing(BinaryOperator<T> op):
         * Returns a Collector which performs a reduction of its input elements under a specified BinaryOperator.
         */
        Optional<Dish> collect = menu.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testReducingWithIdentityAndBinaryOperator() {
        System.out.print("testReducingWithIdentityAndBinaryOperator ---> ");
        /**
         * reducing(T identity, BinaryOperator<T> op):
         *   Returns a Collector which performs a reduction of its input elements under a specified BinaryOperator
         *   using the provided identity.
         */
        Integer collect = menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (dish1, dish2) -> dish1 + dish2));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testReducingWithIdentityAndFunctionAndBinaryOperator() {
        System.out.print("testReducingWithIdentityAndFunctionAndBinaryOperator ---> ");
        /**
         * reducing(U identity, Function<? super T,? extends U> mapper, BinaryOperator<U> op)
         *   Returns a Collector which performs a reduction of its input elements under a
         *   specified mapping function and BinaryOperator.
         */
        Integer collect = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testSummingDouble() {
        System.out.print("testSummingDouble ---> ");
        /**
         * summingDouble(ToDoubleFunction<? super T> mapper):
         * Returns a Collector that produces the sum of a double-valued function applied to the input elements.
         */
        Double collect = menu.stream()
                .collect(Collectors.summingDouble(Dish::getCalories));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testSummingInt() {
        System.out.print("testSummingInt ---> ");
        /**
         * summingInt(ToIntFunction<? super T> mapper):
         * Returns a Collector that produces the sum of a integer-valued function applied to the input elements.
         */
        Integer collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testSummingLong() {
        System.out.print("testSummingLong ---> ");
        /**
         * summingLong(ToLongFunction<? super T> mapper):
         * Returns a Collector that produces the sum of a long-valued function applied to the input elements.
         */
        Long collect = menu.stream()
                .collect(Collectors.summingLong(Dish::getCalories));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToCollection() {
        System.out.print("testToCollection ---> ");
        /**
         * toCollection(Supplier<C> collectionFactory):
         * Returns a Collector that accumulates the input elements into a new Collection, in encounter order.
         */
        LinkedList<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toCollection(LinkedList::new));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithFunction() {
        System.out.print("testToConcurrentMapWithFunction ---> ");
        /**
         * toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper):
         *   Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are
         *   the result of applying the provided mapping functions to the input elements.
         */
        ConcurrentMap<String, Integer> collect = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithFunctionAndBinaryOperator() {
        System.out.print("testToConcurrentMapWithFunctionAndBinaryOperator ---> ");
        /**
         * toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper,
         *   BinaryOperator<U> mergeFunction):
         *      Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are
         *      the result of applying the provided mapping functions to the input elements.
         */
        ConcurrentMap<Dish.Type, Long> collect = menu.stream()
                // (Dish::getType, v -> 1L, (a, b) -> a + b): 记录每种类型的个数
                // value: 只要是一个类型的就给一个
                // (a, b) -> a + b: 将相同的value计数累加
                .collect(Collectors.toConcurrentMap(Dish::getType, value -> 1L, (a, b) -> a + b));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithFunctionAndBinaryOperatorAndSupplier() {
        System.out.print("testToConcurrentMapWithFunctionAndBinaryOperatorAndSupplier ---> ");
        /**
         * toConcurrentMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper,
         *   BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier):
         *      Returns a concurrent Collector that accumulates elements into a ConcurrentMap whose keys and values are
         *      the result of applying the provided mapping functions to the input elements.
         */
        ConcurrentSkipListMap<Dish.Type, Integer> collect = menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType, Dish::getCalories, Integer::sum, ConcurrentSkipListMap::new));
        Optional.of(collect).ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }

    private static void testToList() {
        System.out.print("testToList ---> ");
        /**
         * toList(): Returns a Collector that accumulates the input elements into a new List.
         */
        List<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 700)
                .collect(Collectors.toList());
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToMapFunction() {
        System.out.print("testToMapFunction ---> ");
        /**
         * toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper):
         *   Returns a Collector that accumulates elements into a Map whose keys and values are the result of
         *   applying the provided mapping functions to the input elements.
         */
        Map<String, Dish.Type> collect = menu.stream()
                .collect(Collectors.toMap(Dish::getName, Dish::getType));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToMapFunctionAndBinaryOperator() {
        System.out.print("testToMapFunctionAndBinaryOperator ---> ");
        /**
         * toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper,
         *   BinaryOperator<U> mergeFunction):
         *      Returns a Collector that accumulates elements into a Map whose keys and values are the result of
         *      applying the provided mapping functions to the input elements.
         */
        Map<Dish.Type, Integer> collect = menu.stream()
                .collect(Collectors.toMap(Dish::getType, Dish::getCalories, Integer::sum));
        Optional.of(collect).ifPresent(System.out::println);
    }

    private static void testToMapFunctionAndBinaryOperatorAndSupplier() {
        System.out.print("testToMapFunctionAndBinaryOperatorAndSupplier ---> ");
        /**
         * toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper,
         *   BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier):
         *      Returns a Collector that accumulates elements into a Map whose keys and values are the result of
         *      applying the provided mapping functions to the input elements.
         */
        Hashtable<Dish.Type, Long> collect = menu.stream()
                .collect(Collectors.toMap(Dish::getType, value -> 1L, (a, b) -> a + b, Hashtable::new));
        Optional.of(collect).ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }

    private static void testToSet() {
        System.out.print("testToSet ---> ");
        /**
         * toSet(): Returns a Collector that accumulates the input elements into a new Set.
         */
        Set<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 600)
                .collect(Collectors.toSet());
        Optional.of(collect).ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }

    // 将Map变成线程安全的
    private static void testToMapWithSaft() {
        System.out.print("testToMapWithSaft ---> ");
        Object collect = menu.stream()
                .collect(Collectors
                        .collectingAndThen(Collectors
                                .toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap));
        Optional.of(collect).ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }
}