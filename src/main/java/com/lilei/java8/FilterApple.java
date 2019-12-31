package com.lilei.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 让方法参数具备行动能力
 * @author: Mr.Li
 * @date: Created in 2019/12/30 14:19
 * @version: 1.0
 * @modified By:
 */
public class FilterApple {

    /* 标记在接口上,"函数式接口"是指仅仅只包含一个抽象方法的接口 */
    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }

        return list;
    }

    public static class GreenAnd160WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {

            return (apple.getColor().equals("green") && apple.getWeight() >= 160);
        }
    }

    public static class YellowLess150WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {

            return (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<Apple>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        // List<Apple> greenApples = findGreenApple(list);
        // assert greenApples.size() == 2 : "Algorithm error！";

        System.out.println("----------------------------------------");

        List<Apple> greenApples = findApple(list, "green");
        System.out.println(greenApples);

        List<Apple> redApples = findApple(list, "red");
        System.out.println(redApples);

        System.out.println("----------------------------------------");

        // 策略模式
        List<Apple> resultGerrn = findApple(list, new GreenAnd160WeightFilter());
        System.out.println(resultGerrn);

        // 匿名内部类的方式
        List<Apple> resultYellow = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });
        System.out.println(resultYellow);

        System.out.println("----------------------------------------");

        // Lambda表达式,因为只有一个参数,所以(Apple apple)可以变成apple
        List<Apple> lambdaResult = findApple(list, apple -> {
            return apple.getColor().equals("green") && apple.getWeight() > 160;
        });
        System.out.println(lambdaResult);

        System.out.println("----------------------------------------");

        // 原先的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        // Lambda的方式
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        Thread.currentThread().join();
    }
}