package com.lilei.java8.Future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @description: CompletableFuture流水线工作,join多个异步任务详细讲解
 * @author: Mr.Li
 * @date: Created in 2020/1/16 19:30
 * @version: 1.0
 * @modified By:
 */
public class CompletableFutureInAction3 {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                // 设置守护线程
                runnable -> {
                    Thread thread = new Thread(runnable);
                    thread.setDaemon(false);
                    return thread;
                }
        );

        /**
         * // 如果只有一个
         * CompletableFuture.supplyAsync(CompletableFutureInAction1::getDoubleValue, executor)
         *        /**
         *         * thenApply(Function<? super T,? extends U> fn):
         *         *   Returns a new CompletionStage that, when this stage completes normally,
         *         *   is executed with this stage's result as the argument to the supplied function.
         *         * 类似于流水线
         *        .thenApply(CompletableFutureInAction3::multiply)
         *        .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));
         */

        /**
        // 如果有多个
        List<Integer> productionIDs = Arrays.asList(1, 2, 3, 4, 5);
        // 得到相应个数的随机值
        Stream<CompletableFuture<Double>> completableFutureStream = productionIDs
                .stream().map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::getDoubleValue, executor));
        // 对每个随机值调用multiply()方法
        Stream<CompletableFuture<Double>> multiplyFutures = completableFutureStream
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply));
        // 得到结果的List集合
        // join(): Returns the result value when complete, or throws an (unchecked) exception if completed exceptionally.
        List<Double> result = multiplyFutures.map(CompletableFuture::join).collect(toList());
        // 输出
        Optional.of(result).ifPresent(System.out::println);
         */

        // improve
        Optional.of(Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::getDoubleValue, executor))
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join).collect(toList()))
                .ifPresent(System.out::println);

        executor.shutdown();
    }

    private static double multiply(double value) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }
}
