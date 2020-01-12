package com.lilei.java8.ForkJoinPool;

import java.util.concurrent.ForkJoinPool;

/**
 * @description: Fork Join Pool
 * @author: Mr.Li
 * @date: Created in 2020/1/10 17:25
 * @version: 1.0
 * @modified By:
 * <p>
 * Fork/Join 框架是Java7提供了的一个用于并行执行任务的框架,是一个把大任务分割成若干个小任务,最终汇总每个小任务结果后得到大任务结果的框架;
 * Fork 就是把一个大任务切分为若干子任务并行的执行;
 * Join 就是合并这些子任务的执行结果,最后得到这个大任务的结果;
 * </p>
 */
public class ForkJoinPoolTest {

    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        System.out.println("result => " + calc());

        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println("ForkJoinPool => " + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction => " + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private static int calc() {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }
}
