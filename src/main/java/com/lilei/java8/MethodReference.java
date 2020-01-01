package com.lilei.java8;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @description: Method Reference
 * @author: Mr.Li
 * @date: Created in 2020/1/1 11:50
 * @version: 1.0
 * @modified By:
 */
public class MethodReference {

    public static void main(String[] args) {
        Consumer<String> consumer = (str) -> System.out.println(str);
        userConsumer(consumer, "Hello Java");

        System.out.println("--------------------------");

        userConsumer(str -> System.out.println(str), "Hello Guava");

        System.out.println("--------------------------");

        /**
         * public void println(String x) {
         *    synchronized (this) {
         *        print(x);
         *        newLine();
         *    }
         * }
         *
         * ===> 这个入参是"Hello Alex",只有一个入参
         */
        userConsumer(System.out::println, "Hello Alex");

        List<Apple> list = Arrays.asList(new Apple("green", 110), new Apple("red", 123), new Apple("blue", 110));
        System.out.println(list);

        // 顺序(小到大) ===> blue green red
        list.sort((apple1, apple2) -> apple1.getColor().compareTo(apple2.getColor()));
        System.out.println(list);

        // 反序(大到小)  ===> red green blue
        list.sort((apple1, apple2) -> apple2.getColor().compareTo(apple1.getColor()));
        System.out.println(list);

        System.out.println("--------------------------");

        // 顺序(小到大)  ===> blue green red
        list.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list);

        // Method Reference
        list.stream().forEach(System.out::println);

        int value = Integer.parseInt("123");
        System.out.println(value);

        /**
         * There are three main kinds of method references:
         *
         * 1. A method reference to a static method
         * (for example, the method parseInt of Integer, written Integer::parseInt)
         *
         * 2. A method reference to an instance method of an arbitrary type
         * (for example, the method length of a String, written String::length)
         *
         * 3. A method reference to an instance method of an existing object
         * (for example, suppose you have a local variable expensiveTransaction that holds an object of type Transaction, which
         * supports an instance method getValue; you can write expensiveTransaction::getValue)
         */
        // 1.1
        Function<String, Integer> funParseInt = Integer::parseInt;
        Integer resultApply = funParseInt.apply("123");
        System.out.println(resultApply);

        // 2.1
        Function<String, Integer> funString = String::length;
        resultApply = funString.apply("Hello");
        System.out.println(resultApply);
        // 2.2
        BiFunction<String, Integer, Character> characterBiFunction = String::charAt;
        Character ch = characterBiFunction.apply("Hello", 1);
        System.out.println(ch);

        // 3.1
        String str = new String("Hello");
        Function<Integer, Character> charFunction = str::charAt;
        Character ch2 = charFunction.apply(0);
        System.out.println(ch2);

        Supplier<String> supplier = String::new;
        String strSupplier = supplier.get();
        System.out.println(strSupplier.getClass());

        BiFunction<String, Long, Apple> appleBiFunction = Apple::new;
        Apple apple = appleBiFunction.apply("red", 100L);
        System.out.println(apple);

        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("green", 123L, "FuShi");
        System.out.println(complexApple);

        /**
         * 比较:
         *
         * 1、传统方式
         * list.sort(new Comparator<Apple>() {
         *     @Override
         *     public int compare(Apple o1, Apple o2) {
         *         return o1.getColor().compareTo(o2.getColor());
         *     }
         * }
         *
         * 2、Lambda表达式
         * list.sort((apple1, apple2) -> apple1.getColor().compareTo(apple2.getColor()));
         *
         * 3、Method Reference
         * list.sort(Comparator.comparing(Apple::getColor));
         *
         * 分析3:
         * public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
         *         Function<? super T, ? extends U> keyExtractor) {
         *     Objects.requireNonNull(keyExtractor);
         *     return (Comparator<T> & Serializable)
         *             (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         * }
         *
         * ===> 相当于三种引用的第2种
         * (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         * 其中 keyExtractor ===> Function<? super T, ? extends U> keyExtractor
         */
    }

    private static <T> void userConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }
}
