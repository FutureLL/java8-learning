package com.lilei.java8.ForkJoinPool;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: RecursiveAction 没有返回值
 * @author: Mr.Li
 * @date: Created in 2020/1/10 18:19
 * @version: 1.0
 * @modified By:
 *
 * RecursiveAction:
 *   A recursive resultless ForkJoinTask.
 *   This class establishes conventions to parameterize resultless actions as Void ForkJoinTasks.
 *   Because null is the only valid value of type Void, methods such as join always return null upon completion.
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final int[] data;

    private final int LIMIT = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end - start) <= LIMIT) {
            // int index = 0;
            for (int i = start; i < end; i++) {
                // index += data[i];
                AccumulatorHelper.accumlate(data[i]);
            }
        } else {
            int mid = (start + end) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    static class AccumulatorHelper {

        /**
         * AtomicInteger:
         *   An int value that may be updated atomically.
         *   See the java.util.concurrent.atomic package specification for description of the properties of atomic variables.
         *   An AtomicInteger is used in applications such as atomically incremented counters,
         *   and cannot be used as a replacement for an Integer. However,
         *   this class does extend Number to allow uniform access by tools and utilities that deal with numerically-based classes.
         */
        private static final AtomicInteger result = new AtomicInteger(0);

        static void accumlate(int value) {
            result.getAndAdd(value);
        }

        public static int getResult() {
            return result.get();
        }

        static void rest() {
            result.set(0);
        }
    }
}
