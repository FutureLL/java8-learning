package com.lilei.java8.Example;

/**
 * @description: Example
 * @author: Mr.Li
 * @date: Created in 2020/1/7 13:41
 * @version: 1.0
 * @modified By:
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
