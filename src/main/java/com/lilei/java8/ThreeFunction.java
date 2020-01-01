package com.lilei.java8;

/**
 * @description: 模拟更多的BiFunction
 * @author: Mr.Li
 * @date: Created in 2020/1/1 13:38
 * @version: 1.0
 * @modified By:
 */

@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {

    R apply(T t, U u, K k);
}
