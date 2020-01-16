package com.lilei.java8.Future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2020/1/16 13:58
 * @version: 1.0
 * @modified By:
 */
public class FutureAction2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // ThreadFactoryBuilder()是Google Guava的知识
        /**
         * Class ThreadFactoryBuilder:
         * A ThreadFactory builder, providing any combination of these features:
         *  1、whether threads should be marked as daemon threads
         *  2、a naming format
         *  3、a thread priority
         *  4、an uncaught exception handler
         *  5、a backing thread factory
         *
         * setNameFormat(String nameFormat):
         *   Sets the naming format to use when naming threads (Thread.setName(java.lang.String))
         *   which are created with this ThreadFactory.
         *
         * build(): Returns a new thread factory using the options supplied during the building process.
         */
        ThreadFactory buildThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        // 创建线程池,相当于原来的Executors.newSingleThreadExecutor()
        ExecutorService executor = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                buildThreadFactory);

        Future<String> future = executor.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10000L);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });

        /**
         * get(long timeout, TimeUnit unit):
         *   Waits if necessary for at most the given time for the computation to complete,
         *   and then retrieves its result, if available.
         * 在相应的时间内没有拿到就报错: java.util.concurrent.TimeoutException
         */
        String value = future.get(10, TimeUnit.SECONDS);
        // isDone(): Returns true if this task completed.
        while (!future.isDone()) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
        System.out.println(value);
        executor.shutdown();
    }
}
