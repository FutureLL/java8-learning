package com.lilei.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @description: Lambda Usage [Predicate、Consumer、Function、Supplier]
 * @author: Mr.Li
 * @date: Created in 2019/12/31 13:41
 * @version: 1.0
 * @modified By:
 */
public class LambdaUsage {

    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : source) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : source) {
            if (predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }

        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : source) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                result.add(apple);
            }
        }

        return result;
    }

    private static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple apple : source) {
            consumer.accept(apple);
        }
    }

    private static void simpleBiConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple apple : source) {
            consumer.accept(apple, c);
        }
    }

    private static String testFunction(Apple apple, Function<Apple, String> function) {
        return function.apply(apple);
    }

    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> biFunction) {
        return biFunction.apply(color, weight);
    }

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

        List<Apple> greenList = filter(list, (apple -> apple.getColor().equals("green")));
        System.out.println(greenList);

        List<Apple> weightList = filterByWeight(list, weight -> weight > 120);
        System.out.println(weightList);

        List<Apple> BiList = filterByBiPredicate(list, (str, weight) -> str.equals("green") && weight > 100);
        System.out.println(BiList);

        System.out.println("---------------------------------");

        // 循环打印出来
        simpleTestConsumer(list, apple -> System.out.println(apple));
        System.out.println();
        simpleBiConsumer("XXX", list, (apple, str) -> System.out.println(str + apple.getColor() + ":Weight->" + apple.getWeight()));

        System.out.println("---------------------------------");

        String stringFunction = testFunction(new Apple("yellow", 100), (apple -> apple.toString()));
        System.out.println(stringFunction);

        IntFunction<Double> intFunction = value -> value * 100.0D;
        double doubleFunction = intFunction.apply(10);
        System.out.println(doubleFunction);

        Apple appleBiFunction = testBiFunction("blue", 130, (color, weight) -> new Apple(color, weight));
        System.out.println(appleBiFunction);

        System.out.println("---------------------------------");

        // method inference
        // 得到了String类的无参构造
        Supplier<String> str = String::new;
        System.out.println(str.get().getClass());

        Apple greenSupplier = createApple(() -> new Apple("green", 100));
        System.out.println(greenSupplier);

        System.out.println("---------------------------------");

        /**
         * 这个是Java7的语法
         * 这里默认了这个int类型的i是被final修饰的,所以才可以写,但是如果在Runnable这行下面接着写i++,那么会报错
         * 原因: 推断这个i是被final修饰的,不能进行++操作
         */
        int i = 0;
        Runnable runnable = () -> System.out.println(i);
        // i++;
    }

    /**
     * public static void main(String[] args) {
     *         Runnable runnable1 = () -> System.out.println("Hello");
     *
     *         Runnable runnable2 = new Runnable() {
     *             @Override
     *             public void run() {
     *                 System.out.println("Hello");
     *             }
     *         };
     *
     *         process(runnable1);
     *         process(runnable2);
     *         process(() -> System.out.println("Hello"));
     *     }
     *
     *     private static void process(Runnable runnable) {
     *         runnable.run();
     *     }
     * }
     */

    /**
     *     @FunctionalInterface
     *     public interface Adder {
     *         int add(int a, int b);
     *     }
     *
     *     @FunctionalInterface   // ===>   这里报错,因为相当于有两个add()方法
     *     public interface SmartAdder extends Adder {
     *         int add(long a, long b);
     *     }
     *
     *     @FunctionalInterface
     *     public interface Empty extends Adder {
     *
     *     }
     *
     *     @FunctionalInterface   // ===>   这里报错,因为没有方法
     *     public interface Nothing {
     *
     *     }
     */
}
