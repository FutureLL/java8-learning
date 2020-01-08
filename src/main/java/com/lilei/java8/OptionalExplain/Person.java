package com.lilei.java8.OptionalExplain;

import java.util.Optional;

/**
 * @description: Person Class
 * @author: Mr.Li
 * @date: Created in 2020/1/7 15:38
 * @version: 1.0
 * @modified By:
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return this.car;
    }
}
