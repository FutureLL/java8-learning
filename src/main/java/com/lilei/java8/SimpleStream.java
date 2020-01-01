package com.lilei.java8;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @description: Java 8 in Action of Stream Example
 * @author: Mr.Li
 * @date: Created in 2020/1/1 15:02
 * @version: 1.0
 * @modified By:
 */
public class SimpleStream {

    public static void main(String[] args) {
        // have a dish list (menu)
        List<Dish> menu = Arrays.asList(
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

        List<String> dishNameByCollections = getDishNameByCollections(menu);
        System.out.println(dishNameByCollections);

        List<String> dishNameByStream = getDishNameByStream(menu);
        System.out.println(dishNameByStream);
    }

    private static List<String> getDishNameByStream(List<Dish> menu) {
        // stream(): Returns a sequential with this collection as its source.
        // parallelStream(): Returns a possibly parallel with this collection as its source. It is allowable for this method to return a sequential stream.
        // parallelStream() 运行过程中会有ForkJoinPool表示把一个任务分成了几份,这个代码中分成了7份
        return menu.parallelStream()
                // filter(): Returns a stream consisting of the elements of this stream that match the given predicate.
                .filter(dish -> {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return dish.getCalories() < 400;
                })
                // sorted(): Returns a stream consisting of the elements of this stream, sorted according to the provided.
                .sorted(Comparator.comparing(Dish::getCalories))
                // map(): Returns a stream consisting of the results of applying the given function to the elements of this stream.
                .map(Dish::getName)
                // collect(): Performs a mutable reduction operation on the elements of this stream using a Collector.
                // toList(): Returns a Collector that accumulates the input elements into a new List.
                .collect(toList());
    }

    private static List<String> getDishNameByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();

        // filter and get calories less 400
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // sort
        Collections.sort(lowCalories, (dish1, dish2) -> Integer.compare(dish1.getCalories(), dish2.getCalories()));

        List<String> dishNameList = new ArrayList<>();
        for (Dish dish : lowCalories) {
            dishNameList.add(dish.getName());
        }
        return dishNameList;
    }
}
