package com.lilei.java8;

/**
 * @description:
 * @author: Mr.Li
 * @date: Created in 2020/1/1 15:03
 * @version: 1.0
 * @modified By:
 */
public class Dish {

    private final String name;
    private final boolean vegetartion;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetartion, int calories, Type type) {
        this.name = name;
        this.vegetartion = vegetartion;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetartion() {
        return vegetartion;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetartion=" + vegetartion +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public enum Type {
        MEAT, FISH, OTHER
    }
}
