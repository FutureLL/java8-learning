package com.lilei.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description: Lambda Expression
 * @author: Mr.Li
 * @date: Created in 2019/12/31 11:23
 * @version: 1.0
 * @modified By:
 */
public class LambdaExpression {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        System.out.println(list);

        System.out.println("----------------------------------------");

        // 这里注意没有return如果要写return那么需要加{}
        // (o1, o2) -> o1.getColor().compareTo(o2.getColor()); ==> 如下代码
        // 也可以写成这种方式:  Comparator.comparing(Apple::getColor);
        /**
         * 方式1:
         * (o1, o2) -> {
         *             return o1.getColor().compareTo(o2.getColor());
         *         };
         *
         * 方式2:
         * Comparator.comparing(Apple::getColor);
         */
        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        list = Collections.emptyList();

        list.sort(byColor2);

        System.out.println(list);

        System.out.println("----------------------------------------");

        // 给定一个String类型值,返回Integer类型的值
        Function<String, Integer> function = str -> str.length();

        // 诊断,返回Boolean
        Predicate<Apple> predicate = apple -> apple.getColor().equals("green");

        // 什么都不给返回一个对象
        Supplier<Apple> supplier = () -> new Apple("yellow", 140);

        // 接受一个类型的对象,返回值为void
        Consumer<Apple> consumer = apple -> {};

        Runnable runnable = () -> {};
    }
}
