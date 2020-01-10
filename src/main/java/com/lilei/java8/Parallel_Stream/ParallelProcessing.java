package com.lilei.java8.Parallel_Stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @description: Parallel Processing
 * @author: Mr.Li
 * @date: Created in 2020/1/10 13:25
 * @version: 1.0
 * @modified By:
 *
 *      ArrayList            Excellent
 *      LinkedList           Poor
 *      IntStream.range      Excellent
 *      Stream.iterate       Poor
 *      HashSet              Good
 *      TreeSet              Good
 *  效果: Excellent最好,Good次之,Poor最差
 */
public class ParallelProcessing {

    public static void main(String[] args) {
        // 设置全局变量,配置并行流使用的线程池,可以改变它的大小
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

        // 查看系统内核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println("The best process time(normalAdd) => " + measureSumPerformance(ParallelProcessing::normalAdd, 100_000_000) + " MS");
        System.out.println("===============================================");
        System.out.println("The best process time(iterateStream) => " + measureSumPerformance(ParallelProcessing::iterateStream, 10_000_000) + " MS");
        System.out.println("===============================================");
        System.out.println("The best process time(parallelStream2) => " + measureSumPerformance(ParallelProcessing::parallelStream2, 10_000_000) + " MS");
        System.out.println("===============================================");
        System.out.println("The best process time(parallelStream3) => " + measureSumPerformance(ParallelProcessing::parallelStream3, 100_000_000) + " MS");
    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            // System.out.println("The result of sum ===> " + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        /**
         * iterate(T seed, UnaryOperator<T> f):
         *   Returns an infinite sequential ordered Stream produced by iterative application of a
         *   function f to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
         *
         * parallel(): Returns an equivalent stream that is parallel.
         */
        return Stream.iterate(1L, i -> i + 1)
                .parallel()
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .mapToLong(Long::longValue)
                .parallel()
                .limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream3(long limit) {
        /**
         * rangeClosed(long startInclusive, long endInclusive):
         * Returns a sequential ordered LongStream from startInclusive (inclusive) to endInclusive (inclusive) by an incremental step of 1.
         */
        return LongStream.rangeClosed(1, limit)
                .parallel()
                .reduce(0L, Long::sum);
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i < limit; i++) {
            result += i;
        }
        return result;
    }
}
