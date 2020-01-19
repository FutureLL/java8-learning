package com.lilei.java8.Future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description: CompletableFuture常用API的重点详解
 * @author: Mr.Li
 * @date: Created in 2020/1/19 12:53
 * @version: 1.0
 * @modified By:
 */
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {

        /**
         * supplyAsync(Supplier<U> supplier):
         *   Returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.
         *   commonPool() with the value obtained by calling the given Supplier.
         */
        CompletableFuture.supplyAsync(() -> 1)
                /**
                 * thenApply(Function<? super T,? extends U> fn):
                 *   Returns a new CompletionStage that, when this stage completes normally,
                 *   is executed with this stage's result as the argument to the supplied function.
                 */
                .thenApply(i -> Integer.sum(i, 10))
                /**
                 * whenCompleteAsync(BiConsumer<? super T,? super Throwable> action):
                 *   Returns a new CompletionStage with the same result or exception as this stage, and when this stage completes,
                 *   executes the given action executes the given action using this stage's default asynchronous execution facility,
                 *   with the result (or null if none) and the exception (or null if none) of this stage as arguments.
                 */
                .whenCompleteAsync((t, v) -> System.out.println(t));

        CompletableFuture.supplyAsync(() -> 1)
                /**
                 * handle(BiFunction<? super T,Throwable,? extends U> fn):
                 *   Returns a new CompletionStage that, when this stage completes either normally or exceptionally,
                 *   is executed with this stage's result and exception as arguments to the supplied function.
                 * 跟thenApply()相比多了一个对异常的考虑
                 */
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(System.out::println);
                })
                /**
                 * thenRun(Runnable action):
                 *   Returns a new CompletionStage that, when this stage completes normally, executes the given action.
                 */
                .thenRun(() -> System.out.println("再执行thenRun()方法"));

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                /**
                 * thenAccept(Consumer<? super T> action):
                 *   Returns a new CompletionStage that, when this stage completes normally,
                 *   is executed with this stage's result as the argument to the supplied action.
                 */
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                /**
                 * thenCompose(Function<? super T,? extends CompletionStage<U>> fn):
                 *   Returns a new CompletionStage that, when this stage completes normally,
                 *   is executed with this stage as the argument to the supplied function.
                 */
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                /**
                 * thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn):
                 *   Returns a new CompletionStage that, when this and the other given stage both complete normally,
                 *   is executed with the two results as arguments to the supplied function.
                 */
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                /**
                 * thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action):
                 *   Returns a new CompletionStage that, when this and the other given stage both complete normally,
                 *   is executed with the two results as arguments to the supplied action.
                 */
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                    System.out.println(r1 + r2);
                });

        TimeUnit.MILLISECONDS.sleep(1000L);
    }


}
