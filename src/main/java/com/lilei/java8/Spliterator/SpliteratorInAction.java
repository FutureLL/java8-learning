package com.lilei.java8.Spliterator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @description: Spliterator In Action
 * @author: Mr.Li
 * @date: Created in 2020/1/12 12:05
 * @version: 1.0
 * @modified By:
 * <p>
 * Spliterator:
 * An object for traversing and partitioning elements of a source.
 * The source of elements covered by a Spliterator could be, for example, an array, a Collection, an IO channel,
 * or a generator function.
 *
 * Spliterator是java1.8引入的一种并行遍历的机制    ===>  并行遍历
 * Iterator提供也提供了对集合数据进行遍历的能力    ===>  顺序遍历
 * </p>
 */
public class SpliteratorInAction {

    // OfInt: A Spliterator specialized for int values.

    private static String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut rutrum eget velit in vehicula. " +
            "\n" +
            "Integer fermentum faucibus nisi, vel semper libero pellentesque laoreet. Praesent cursus risus in diam pharetra, non congue mi facilisis. " +
            "\n" +
            "Duis sed tincidunt nulla, nec sagittis purus. Fusce facilisis lorem quis gravida cursus. " +
            "\n" +
            "Maecenas in nisl lobortis, malesuada diam at, bibendum arcu. In augue magna, hendrerit eu massa consequat, convallis aliquet ligula. " +
            "\n" +
            "Sed gravida augue nec mattis blandit. Nulla facilisi. In hac habitasse platea dictumst. Sed ut diam libero. " +
            "\n" +
            "Donec in nulla tellus. Donec cursus porta efficitur. Maecenas dictum enim nec dui consequat posuere. Vivamus ultricies imperdiet velit sit amet gravida.\n" +
            "\n" +
            "Quisque id justo eu arcu consectetur feugiat dictum ac mauris. Ut et eros enim. Proin in dui viverra, tincidunt ligula vel, malesuada ipsum. " +
            "\n" +
            "Donec eu eros metus. Vivamus auctor vehicula tristique. Sed molestie, ligula vitae semper sagittis, nisi ante venenatis mauris, et volutpat ipsum nibh et ipsum. " +
            "\n" +
            "Vestibulum at molestie ipsum. Praesent pellentesque cursus nisl nec tempus. Aliquam augue lorem, suscipit eget arcu eu, iaculis sagittis lectus.\n" +
            "\n" +
            "Duis quis augue risus. Ut risus velit, tincidunt vestibulum efficitur ut, aliquet non nibh. Vestibulum sodales venenatis libero ac hendrerit. " +
            "\n" +
            "Fusce blandit, massa nec auctor varius, tortor nisl euismod magna, vel maximus ligula mauris sit amet tellus. Aliquam euismod ac risus blandit vulputate. " +
            "\n" +
            "Ut elementum tempor egestas. Donec nec commodo dui. Nullam at magna metus.\n" +
            "\n" +
            "Etiam id ipsum vitae lectus egestas condimentum. Phasellus dictum sapien vel felis ullamcorper placerat. Donec pretium felis ante, nec venenatis dui ultricies ut. " +
            "\n" +
            "Donec feugiat, nisl et posuere gravida, massa purus imperdiet ipsum, ut semper ipsum lacus et ligula. Nullam viverra dui interdum diam accumsan ullamcorper. " +
            "\n" +
            "Pellentesque nec leo aliquam, dapibus nisi sit amet, lobortis sem. Curabitur finibus lobortis dui, hendrerit suscipit magna dapibus nec. Donec commodo bibendum turpis. Integer erat nibh, aliquam sed est nec, ullamcorper congue nisl. " +
            "\n" +
            "Nulla facilisis libero nec enim tincidunt, ut mollis ligula scelerisque. Donec at arcu sit amet neque malesuada tristique ut condimentum leo. Phasellus interdum erat et facilisis vulputate. Curabitur id arcu sed ipsum consectetur elementum id ac augue.\n" +
            "\n" +
            "In in odio tellus. Donec pulvinar pellentesque interdum. Morbi eu eros vitae mi eleifend finibus pretium vel neque. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam ac sagittis arcu. Mauris at cursus sem, quis auctor erat. " +
            "\n" +
            "Praesent ac egestas augue. Mauris orci libero, interdum ut egestas ut, egestas nec felis. Nunc suscipit, augue ac ultricies lacinia, sapien lacus mattis ex, consequat sagittis mauris libero non ipsum. Mauris ultrices, mi in ultricies egestas, arcu ex maximus dui, sit amet condimentum augue lorem maximus mauris." +
            "\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "\n" +
            "Ut rutrum eget velit in vehicula. Integer fermentum faucibus nisi, vel semper libero pellentesque laoreet. Praesent cursus risus in diam pharetra, non congue mi facilisis. Duis sed tincidunt nulla, nec sagittis purus. Fusce facilisis lorem quis gravida cursus. Maecenas in nisl lobortis, malesuada diam at, bibendum arcu. " +
            "\n" +
            "In augue magna, hendrerit eu massa consequat, convallis aliquet ligula. " +
            "\n" +
            "Sed gravida augue nec mattis blandit. Nulla facilisi. In hac habitasse platea dictumst. Sed ut diam libero. Donec in nulla tellus. Donec cursus porta efficitur. Maecenas dictum enim nec dui consequat posuere. Vivamus ultricies imperdiet velit sit amet gravida.\n" +
            "\n" +
            "Quisque id justo eu arcu consectetur feugiat dictum ac mauris. Ut et eros enim. Proin in dui viverra, tincidunt ligula vel, malesuada ipsum. Donec eu eros metus. Vivamus auctor vehicula tristique. Sed molestie, ligula vitae semper sagittis, nisi ante venenatis mauris, et volutpat ipsum nibh et ipsum. Vestibulum at molestie ipsum. " +
            "\n" +
            "Praesent pellentesque cursus nisl nec tempus. …";

    public static void main(String[] args) {
        MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
        // 总行数
        Optional.ofNullable(mySpliteratorText.stream().count())
                .ifPresent(System.out::println);

        System.out.println("---------------");

        // 输出全文
        mySpliteratorText.stream()
                .forEach(System.out::println);

        System.out.println("---------------");

        // 去掉换行符
        mySpliteratorText.stream().filter(s -> !s.equals(""))
                .forEach(System.out::println);

        System.out.println("---------------");

        // 去掉换行符,并且为并行的
        mySpliteratorText.parallelStream()
                .filter(s -> !s.equals(""))
                .forEach(System.out::println);

        System.out.println("=========================================");

        List<String> list = Arrays.asList("Apple", "Banana", "Orange");
        Spliterator<String> spliteratorList = list.spliterator();
        /**
         * forEachRemaining(Consumer<? super Integer> action):
         *   Performs the given action for each remaining element, sequentially in the current thread,
         *   until all elements have been processed or the action throws an exception.
         */
        System.out.println(" =====> build traversal");
        spliteratorList.forEachRemaining(System.out::println);

        Spliterator<String> spliteratorList1 = list.spliterator();
        /**
         * tryAdvance(Consumer<? super Integer> action):
         *   If a remaining element exists, performs the given action on it, returning true; else returns false.
         */
        System.out.println(" =====> attempting try advance again");
        Consumer<String> consumer = str -> System.out.println("I don't want to output everything");
        System.out.println(spliteratorList1.tryAdvance(consumer));

        List<String> listString = Arrays.asList("Apple", "Banana", "Orange");
        Spliterator<String> spliterator = listString.spliterator();
        /**
         * trySplit():
         *   If this spliterator can be partitioned, returns a Spliterator covering elements, that will,
         *   upon return from this method, not be covered by this Spliterator.
         */
        Spliterator<String> spliteratorSplit = spliterator.trySplit();

        System.out.println("=========================================");

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Spliterator.OfInt spliteratorOfInt = Arrays.spliterator(arr, 2, 6);
        IntConsumer intConsumer = indexValue -> System.out.println(indexValue);
        spliteratorOfInt.tryAdvance(intConsumer);
        spliteratorOfInt.tryAdvance(intConsumer);
        spliteratorOfInt.tryAdvance(intConsumer);
        spliteratorOfInt.tryAdvance(intConsumer);
        spliteratorOfInt.tryAdvance(intConsumer);
    }

    static class MySpliteratorText {
        private final String[] data;

        MySpliteratorText(String text) {
            Objects.requireNonNull(text, "The parameter can not be null");

            this.data = text.split("\n");
        }

        public Stream<String> stream() {
            return StreamSupport.stream(new MySpliterator(), false);
        }

        public Stream<String> parallelStream() {
            return StreamSupport.stream(new MySpliterator(), true);
        }

        private class MySpliterator implements Spliterator<String> {

            private int start, end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            /**
             * tryAdvance(Consumer<? super Integer> action):
             * If a remaining element exists, performs the given action on it, returning true; else returns false.
             */
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start <= end) {
                    action.accept(MySpliteratorText.this.data[start++]);
                    return true;
                }
                return false;
            }

            /**
             * trySplit():
             * If this spliterator can be partitioned, returns a Spliterator covering elements, that will,
             * upon return from this method, not be covered by this Spliterator.
             */
            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;
                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                // 特征值: 不可变,大小固定,可以取子集的
                return IMMUTABLE | SIZED | SUBSIZED;
            }
        }
    }
}
