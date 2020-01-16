package com.lilei.java8.Future;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 模拟一个CompletableFuture
 * @author: Mr.Li
 * @date: Created in 2020/1/16 15:26
 * @version: 1.0
 * @modified By:
 */
public class FutureAction3 {

    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "I am error.";
            }
        });

        // 注册一个事件
        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String str) {
                System.out.println(str);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("Error");
                cause.printStackTrace();
            }
        });

        System.out.println("........................");
        System.out.println(future.get());
        System.out.println(future.get());
    }

    /* 非阻塞的方式 */
    private static <T> Future<T> invoke(Callable<T> callable) {

        /**
         * AtomicReference:
         *   An object reference that may be updated atomically.
         *   See the java.util.concurrent.
         *   atomic package specification for description of the properties of atomic variables.
         */
        AtomicReference<T> result = new AtomicReference<>();
        /**
         * AtomicBoolean:
         *   A boolean value that may be updated atomically.
         *   See the java.util.concurrent.
         *   atomic package specification for description of the properties of atomic variables.
         *   An AtomicBoolean is used in applications such as atomically updated flags, and cannot be used as a replacement for a Boolean.
         */
        AtomicBoolean finished = new AtomicBoolean(false);

        Future future = new Future<T>() {

            private Completable<T> completable;

            @Override
            public T get() {
                // get(): Gets the current value.
                return result.get();
            }

            @Override
            public boolean isDone() {
                // get(): Returns the current value.
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };

        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Throwable cause) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(cause);
                }
            }
        });
        t.start();

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable cause);
    }
}
