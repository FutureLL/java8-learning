package com.lilei.java8.ForkJoinPool;

import java.util.concurrent.RecursiveTask;

/**
 * @description: RecursiveTask 有返回值
 * @author: Mr.Li
 * @date: Created in 2020/1/10 17:47
 * @version: 1.0
 * @modified By:
 * <p>
 * RecursiveTask:
 * A recursive result-bearing ForkJoinTask.
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final int[] data;

    private final int LIMIT = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= LIMIT) {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }
        int mid = (start + end) / 2;

        AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
        AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
        /**
         * fork():
         *   Arranges to asynchronously execute this task in the pool the current task is running in,
         *   if applicable, or using the ForkJoinPool.commonPool() if not inForkJoinPool().
         */
        left.fork();
        Integer rightResult = right.compute();
        /**
         * join():
         *   Returns the result of the computation when it is done.
         */
        Integer leftResult = left.join();
        return rightResult + leftResult;
    }
}
