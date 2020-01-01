package com.lilei.java8;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2020/1/1 13:34
 * @version: 1.0
 * @modified By:
 */
public class ComplexApple {

    private String color;
    private long weight;
    private String name;

    public ComplexApple(String color, long weight, String name) {
        this.color = color;
        this.weight = weight;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public long getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ComplexApple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
