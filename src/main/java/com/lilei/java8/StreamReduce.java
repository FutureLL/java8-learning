package com.lilei.java8;

import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @description: Stream Reduce
 * @author: Mr.Li
 * @date: Created in 2020/1/6 15:34
 * @version: 1.0
 * @modified By:
 */
public class StreamReduce {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        // reduce(T identity, BinaryOperator<T> accumulator):
        // Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value.
        Integer result = stream.reduce(0, (i, j) -> i + j);
        System.out.println(result);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::sum).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::max).ifPresent(System.out::println);

        // 与max效果相同
        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i, j) -> i > j ? i : j).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.filter(i -> i%2 == 0).reduce((i, j) -> i*j).ifPresent(System.out::println);
    }
}
