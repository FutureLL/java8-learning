package com.lilei.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @description: Stream Map
 * @author: Mr.Li
 * @date: Created in 2020/1/6 14:01
 * @version: 1.0
 * @modified By:
 */
public class StreamMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);

        List<Integer> result = list.stream().map(index -> index * 2).collect(toList());
        System.out.println(result);

        listDish().stream().map(dish -> dish.getName()).forEach(System.out::println);

        List<String> dishs = listDish().stream().map(dish -> dish.getName()).collect(toList());
        System.out.println(dishs);

        // flatMap flat(扁平化)
        // flatMap(Function<? super T,? extends Stream<? extends R>> mapper):
        // Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        String[] words = {"Hello", "World"};
        // {H, e, l, l, o}, {W, o, r, l, d}
        Stream<String[]> stream = Arrays.stream(words).map(word -> word.split(""));
        // H, e, l, l, o, W, o, r, l, d
        // stream(T[] array): Returns a sequential Stream with the specified array as its source.
        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        stringStream.distinct().forEach(System.out::println);
    }

    private static List<Dish> listDish() {
        return Arrays.asList(
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
    }
}
