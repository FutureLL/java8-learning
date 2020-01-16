package com.lilei.java8.Future;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: Future Action
 * @author: Mr.Li
 * @date: Created in 2020/1/16 12:56
 * @version: 1.0
 * @modified By:
 * <p>
 * Block阻塞式与Future非阻塞式对比
 */
public class FutureAction {

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        // 非阻塞的方式可以干其他的事情
        // get(): Waits if necessary for the computation to complete, and then retrieves its result.
        System.out.println(future.get());
        System.out.println(future.get());

        // If not finish, then wait
        // 如果是false那么继续执行也就是等待,直到为true
        while (!future.isDone()) {
            TimeUnit.MILLISECONDS.sleep(10);
        }
        // 完成执行之后会自动返回
        System.out.println(future.get());

        System.out.println("========================");

        String value = block(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        // 一直阻塞知道全部执行完成之后才会执行下面的代码
        System.out.println(value);
    }

    /* 以前阻塞式的方式 */
    private static <T> T block(Callable<T> callable) {
        return callable.action();
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
        Thread t = new Thread(() -> {
            T value = callable.action();
            // set(V newValue): Sets to the given value.
            result.set(value);
            // set(boolean newValue): Unconditionally sets to the given value.
            finished.set(true);
        });
        t.start();

        Future future = new Future<T>() {
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
        };

        return future;
    }

    interface Future<T> {
        T get();

        boolean isDone();
    }

    interface Callable<T> {
        T action();
    }
}
