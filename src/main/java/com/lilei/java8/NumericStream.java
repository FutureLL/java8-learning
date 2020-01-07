package com.lilei.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @description: Numeric Stream
 * @author: Mr.Li
 * @date: Created in 2020/1/7 12:19
 * @version: 1.0
 * @modified By:
 */
public class NumericStream {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer result = stream.filter(index -> index.intValue() > 3).reduce(0, Integer::sum);
        System.out.println(result);

        System.out.println("--------------------------");

        // 使用具体的Stream,减少内存的使用
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        IntStream intStream = stream.mapToInt(index -> index.intValue());
        int sum = intStream.filter(index -> index > 3).sum();
        // stream.mapToInt(index -> index.intValue()).filter(index -> index > 3).reduce(0, (i, j) -> i + j);
        System.out.println(sum);

        System.out.println("--------------------------");

        // 和9有关的勾股定理组成
        int a = 9;
        // rangeClosed(int startInclusive, int endInclusive):
        // Returns a sequential ordered IntStream from startInclusive (inclusive) to endInclusive (inclusive) by an incremental step of 1.
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                // boxed(): Returns a Stream consisting of the elements of this stream, each boxed to an Integer.
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a = " + r[0] + ", b = " + r[1] + ", c = " + r[2]));

        System.out.println("--------------------------");

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                // mapToObj(IntFunction<? extends U> mapper):
                // Returns an object-valued Stream consisting of the results of applying the given function to the elements of this stream.
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a = " + r[0] + ", b = " + r[1] + ", c = " + r[2]));
    }
}
