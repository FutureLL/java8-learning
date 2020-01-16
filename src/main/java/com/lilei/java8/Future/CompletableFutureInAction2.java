package com.lilei.java8.Future;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description: CompletableFuture之supplyAsync详细介绍
 * @author: Mr.Li
 * @date: Created in 2020/1/16 18:36
 * @version: 1.0
 * @modified By:
 */
public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean finished = new AtomicBoolean(false);

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
         * supplyAsync(Supplier<U> supplier):
         *   Returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.
         *   commonPool() with the value obtained by calling the given Supplier.
         *
         * supplyAsync(Supplier<U> supplier, Executor executor):
         *   Returns a new CompletableFuture that is asynchronously completed by a task running in the given executor
         *   with the value obtained by calling the given Supplier.
         */
        CompletableFuture.supplyAsync(CompletableFutureInAction1::getDoubleValue, executor)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(throwable -> throwable.printStackTrace());

                    finished.set(true);
                });

        executor.shutdown();
        System.out.println("=== I am no --- block ---");

        /**
         * 不会打印出任何结果
         *      原因: 执行任务的线程是守护线程
         *      解决: 该线程不是守护线程就可以了

         // 方法1: 会抛出一个中断异常, InterruptedException
         Thread.currentThread().join();
         // 方法2: 进行一个判断,没有完成那么等待
         while (!finished.get()) {
         TimeUnit.MILLISECONDS.sleep(10);
         }
         // 方法3: ThreadPoolExecutor executor = new ThreadPoolExecutor(......), 创建一个线程池并将线程池设置为守护线程
         */
    }
}
