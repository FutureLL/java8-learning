package com.lilei.java8.Future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description: CompletableFuture用法入门
 * @author: Mr.Li
 * @date: Created in 2020/1/16 16:45
 * @version: 1.0
 * @modified By:
 */
public class CompletableFutureInAction1 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {

        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = getDoubleValue();
            // complete(T value):
            // If not already completed, sets the value returned by get() and related methods to the given value.
            // 把value值给completableFuture对象
            completableFuture.complete(value);
        }).start();

        System.out.println("=== no === block ===");

        // 需要显示调用
        // get(): Waits if necessary for this future to complete, and then returns its result.
        Optional.ofNullable(completableFuture.get())
                .ifPresent(System.out::println);

        System.out.println("=====================");

        /**
         * 没有显示的调用结果
         * whenComplete(BiConsumer<? super T,? super Throwable> action):
         *   Returns a new CompletionStage with the same result or exception as this stage, and when this stage completes,
         *   executes the given action with the result (or null if none) and the exception (or null if none) of this stage.
         */
        completableFuture.whenComplete((v,t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(throwable -> throwable.printStackTrace());
        });
    }

    /* 包可见的状态 */
    static double getDoubleValue() {
        try {
            TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double value = RANDOM.nextDouble();
        System.out.println(value);
        // 返回一个随机的double类型
        return value;
    }
}
