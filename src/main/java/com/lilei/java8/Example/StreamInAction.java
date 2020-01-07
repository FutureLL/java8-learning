package com.lilei.java8.Example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @description: Putting it all into practice
 * @author: Mr.Li
 * @date: Created in 2020/1/7 13:45
 * @version: 1.0
 * @modified By:
 */
public class StreamInAction {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1、Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> result = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        // [{Trader:Brian in Cambridge, year: 2011, value: 300}, {Trader:Raoul in Cambridge, year: 2011, value: 400}]
        System.out.println(result);

        System.out.println("-------------------------------");

        // 2、What are all the unique cities where the traders work?
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct()
                // out: Cambridge
                //      Milan
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        // 3、Find all traders from Cambridge and sort them by name.
        transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        // 4、Return a string of all traders’ names sorted alphabetically.
        Optional<String> value = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((name1, name2) -> name1 + ", " + name2);
        System.out.println(value);

        System.out.println("-------------------------------");

        // 5、Are any traders based in Milan?
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));

        System.out.println("-------------------------------");

        // 6、Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        // 7、What’s the highest value of all the transactions?
        Integer maxValue = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::max)
                .get();
        System.out.println(maxValue);

        System.out.println("-------------------------------");

        // 8、Find the transaction with the smallest value.
        Integer minValue = transactions.stream().map(Transaction::getValue)
                .reduce(Integer::min)
                .get();
        System.out.println(minValue);
    }
}
