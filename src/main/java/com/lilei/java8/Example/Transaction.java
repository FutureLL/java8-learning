package com.lilei.java8.Example;

/**
 * @description: Transaction Class
 * @author: Mr.Li
 * @date: Created in 2020/1/7 13:42
 * @version: 1.0
 * @modified By:
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader +
                ", year: " + this.year +
                ", value: " + this.value + '}';
    }
}
