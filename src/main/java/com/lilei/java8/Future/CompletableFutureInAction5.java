package com.lilei.java8.Future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @description: CompletableFuture常用API的重点详解
 * @author: Mr.Li
 * @date: Created in 2020/1/19 16:36
 * @version: 1.0
 * @modified By:
 */
public class CompletableFutureInAction5 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            /**
             * runAfterBoth(CompletionStage<?> other, Runnable action):
             *   Returns a new CompletionStage that, when this and the other given stage both complete normally,
             *   executes the given action.
             * 在两个CompletableFuture都结束之后才打印done
             */
            System.out.println(Thread.currentThread().getName() + " is running.");
            return 2;
        }), () -> System.out.println("done"));


        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return 10;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            /**
             * applyToEither(CompletionStage<? extends T> other, Function<? super T,U> fn):
             *   Returns a new CompletionStage that, when either this or the other given stage complete normally,
             *   is executed with the corresponding result as argument to the supplied function.
             * 只对其中一个值进行 10*v 运算之后运算结束
             */
            System.out.println("I am future 2");
            return 20;
        }), v -> 10 * v).thenAccept(System.out::println);


        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future acceptEither()1");
            return 3;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            /**
             * acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action):
             *   Returns a new CompletionStage that, when either this or the other given stage complete normally,
             *   is executed with the corresponding result as argument to the supplied action.
             * 并且任意返回两个值的其中一个
             */
            System.out.println("I am future acceptEither()2");
            return 4;
        }), System.out::println);


        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future runAfterEither()1");
            return 1;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            /**
             * runAfterEither(CompletionStage<?> other, Runnable action):
             *   Returns a new CompletionStage that, when either this or the other given stage complete normally,
             *   executes the given action.
             */
            System.out.println("I am future runAfterEither()2");
            return 2;
        }), () -> System.out.println("done."));

        System.out.println("============");

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::getDoubleValue))
                .collect(toList());
        /**
         * allOf(CompletableFuture<?>... cfs):
         *   Returns a new CompletableFuture that is completed when all of the given CompletableFutures complete.
         * 处理完所有才会输出done
         */
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done === allOf"));

        TimeUnit.SECONDS.sleep(5);

        System.out.println("=============");

        List<CompletableFuture<Integer>> collect1 = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::getIntegerValue))
                .collect(toList());
        /**
         * anyOf(CompletableFuture<?>... cfs)
         *   Returns a new CompletableFuture that is completed when any of the given CompletableFutures complete,
         *   with the same result.
         * 只处理一个就会输出done,就算后边也输出但是不会进行处理
         */
        CompletableFuture.anyOf(collect1.toArray(new CompletableFuture[collect1.size()]))
                .thenRun(() -> System.out.println("done === anyOf"));

        Thread.currentThread().join();
    }
}
