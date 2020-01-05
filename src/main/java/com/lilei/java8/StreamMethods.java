package com.lilei.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @description: Stream Methods
 * @author: Mr.Li
 * @date: Created in 2020/1/5 15:07
 * @version: 1.0
 * @modified By:
 */
public class StreamMethods {

    public static void main(String[] args) {
        // 流的初始化与转换：
        // of(T... values): Returns a sequential ordered stream whose elements are the specified values.
        Stream stream = Stream.of("a", "b", "c");
        // of(T t): Returns a sequential Stream containing a single element.
        // 1、数组转换为一个流:
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        // 2、集合对象转换为一个流(Collections):
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // 遍历操作(map):
        // map(): Returns a stream consisting of the results of applying the given function to the elements of this stream.
        // 1、遍历转换为大写:
        List<String> output = Arrays.asList("a", "b", "c").stream()
                .map(String::toUpperCase)
                .collect(toList());
        // 2、平方数:
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream()
                .map(n -> n * n)
                .collect(toList());

        // 过滤操作(filter):
        // filter(Predicate<? super T> predicate): Returns a stream consisting of the elements of this stream that match the given predicate.
        // 1、得到其中不为空的String:
        List<String> filterLists = new ArrayList<>();
        filterLists.add("");
        filterLists.add("a");
        filterLists.add("b");
        List afterFilterLists = filterLists.stream()
                .filter(s -> !s.isEmpty())
                .collect(toList());


        // 循环操作(forEach):
        List<String> forEachLists = new ArrayList<>();
        forEachLists.add("a");
        forEachLists.add("b");
        forEachLists.add("c");
        forEachLists.stream().forEach(s -> System.out.println(s));

        // 返回特定的结果集合(limit/skip):
        // limit(): Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        // skip(long n): Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream.
        forEachLists = new ArrayList<>();
        forEachLists.add("a");
        forEachLists.add("b");
        forEachLists.add("c");
        forEachLists.add("d");
        forEachLists.add("e");
        forEachLists.add("f");
        List<String> limitLists = forEachLists.stream().skip(2).limit(3).collect(toList());

        // 排序(sort/min/max/distinct):
        // sorted(): Returns a stream consisting of the elements of this stream, sorted according to natural order.
        List<Integer> sortLists = new ArrayList<>();
        sortLists.add(1);
        sortLists.add(4);
        sortLists.add(6);
        sortLists.add(3);
        sortLists.add(2);
        List<Integer> afterSortLists = sortLists.stream()
                .sorted((In1, In2) -> In1 - In2)
                .collect(Collectors.toList());

        // min(Comparator<? super T> comparator): Returns the minimum element of this stream according to the provided Comparator.
        // max(Comparator<? super T> comparator): Returns the maximum element of this stream according to the provided Comparator.
        List<String> maxLists = new ArrayList<>();
        maxLists.add("a");
        maxLists.add("b");
        maxLists.add("c");
        maxLists.add("d");
        maxLists.add("e");
        maxLists.add("f");
        maxLists.add("hello");
        int maxLength = maxLists.stream()
                .mapToInt(str -> str.length())
                .max()
                .getAsInt();
        System.out.println("字符串长度最长的长度为" + maxLength);

        // distinct(): Returns a stream consisting of the distinct elements (according to Object.equals(Object)) of this stream.
        List<String> distinctList = new ArrayList<>();
        distinctList.add("a");
        distinctList.add("a");
        distinctList.add("c");
        distinctList.add("d");
        List<String> afterDistinctList = distinctList.stream()
                .distinct()
                .collect(toList());

        // 匹配(Match方法):
        // anyMatch(Predicate<? super T> predicate): Returns whether any elements of this stream match the provided predicate.
        List<String> matchList = new ArrayList<>();
        matchList.add("a");
        matchList.add("a");
        matchList.add("c");
        matchList.add("d");
        boolean isExits = matchList.stream().anyMatch(s -> s.equals("c"));

        // noneMatch(Predicate<? super T> predicate): Returns whether no elements of this stream match the provided predicate.
        matchList = new ArrayList<>();
        matchList.add("a");
        matchList.add("");
        matchList.add("a");
        matchList.add("c");
        matchList.add("d");
        boolean isNotEmpty = matchList.stream().noneMatch(s -> s.isEmpty());

        // allMatch(Predicate<? super T> predicate): Returns whether all elements of this stream match the provided predicate.
        matchList = new ArrayList<>();
        matchList.add("a");
        matchList.add("b");
        matchList.add("c");
        matchList.add("d");
        matchList.add("e");
        boolean moneMatch = matchList.stream().allMatch(s -> s.length() == 1);
    }
}
