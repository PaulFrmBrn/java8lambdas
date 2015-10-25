import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 *
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world");

        // 1. a
        List<String> example1before = Stream.of("a", "b", "hello").map(string -> string.toUpperCase()).collect(toList());
        System.out.println("#1 before = " + example1before);
        List<String> example1after = Stream.of("a", "b", "hello").map(String::toUpperCase).collect(toList());
        System.out.println("#1 after = " + example1after);

        // 1.b
        Integer example2before = Stream.of(1, 2, 3).reduce(0, (accumulator, element) -> accumulator + element);
        System.out.println("#2 before = " + example2before);
        Integer example2after = Stream.of(1, 2, 3).reduce(0, Integer::sum);
        System.out.println("#2 after = " + example2after);

        // 1.c
        List<Integer> example3before = Stream.of(asList(1, 2), asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(toList());
        System.out.println("#3 before = " + example3before);
        List<Integer> example3after = Stream.of(asList(1, 2), asList(3, 4)).flatMap(Collection::stream).collect(toList());
        System.out.println("#3 after = " + example3after);

        // 2.a my
        String[] artists = {"John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe"};

        Optional<String> name2max = Stream.of(artists).max((o1, o2) -> {
            if (o1.length() > o2.length()) return 1;
            if (o2.length() > o1.length()) return -1;
            return 0;
        });
        System.out.println("name2max = " +  name2max);

        Optional<String> name2collector = Stream.of(artists).collect(Collectors.maxBy((o1, o2) -> {
            if (o1.length() > o2.length()) return 1;
            if (o2.length() > o1.length()) return -1;
            return 0;
        }));
        System.out.println("name2collector = " + name2collector);

        String name2reduce = Stream.of(artists).reduce(null,(s, s2) -> {
            if (s == null) return s2;
            if (s.length() > s2.length()) return s;
            else return s2;
        });
        System.out.println("name2reduce = " + name2reduce);

        // 2.a fixed

        Optional<String> name2maxFixed = Stream.of(artists).max(byLength);
        System.out.println("name2maxFixed = " +  name2maxFixed);

        Optional<String> name2collectorFixed = Stream.of(artists).collect(Collectors.maxBy(byLength));
        System.out.println("name2collectorFixed = " + name2collectorFixed);

        // Optional<T> reduce(BinaryOperator<T> accumulator); has no identity params & return optional
        Optional<String> name2reduceFixed = Stream.of(artists).reduce((s, s2) -> {
            if (s.length() > s2.length()) return s;
            else return s2;
        });
        System.out.println("name2reduceFixed = " + name2reduceFixed);

        // BinaryOperator.maxBy implements logic of finding max by reduce in the answers
        Optional<String> name2reduceFixed2 = Stream.of(artists).reduce(BinaryOperator.maxBy(byLength));
        System.out.println("name2reduceFixed2 = " + name2reduceFixed2);

        // 2.b
        String[] names = {"John", "Paul", "George", "John", "Paul", "John" ,"John"};
        Map<String, Integer> names2toCount = Stream.of(names).collect(toMap(name -> name, s -> 1, (integer, integer2) -> integer + integer2));
        System.out.println("names2toCount = " + names2toCount);

        // 2.b fixed
        Map<String, Long> names2toCountFixed = Stream.of(names).collect(groupingBy(name -> name, counting()));
        System.out.println("names2toCountFixed = " + names2toCountFixed);

        // 2.c
        String[] names2 = {"1John", "1Paul", "1George1", "2John", "2Paul", "3John" ,"4John"};
        Map<String, List<String>> collect2с1 = Stream.of(names2).collect(new GroupingByCollector<>(s -> s.substring(0, 2), String::toUpperCase));
        System.out.println("collect2с1 = " + collect2с1);
        Map<Integer, List<String>> collect2с2 = Stream.of(names2).collect(new GroupingByCollector<>(s -> Integer.valueOf(s.substring(0, 1)), String::toUpperCase));
        System.out.println("collect2с2 = " + collect2с2);

        // 2.c fixed
        Map<String, List<String>> collect2с1fixed = Stream.of(names2).collect(new GroupingByCollectorFixed<>(s -> s.substring(0, 2), String::toUpperCase));
        System.out.println("collect2с1fixed = " + collect2с1fixed);
        Map<Integer, List<String>> collect2с2fixed = Stream.of(names2).collect(new GroupingByCollectorFixed<>(s -> Integer.valueOf(s.substring(0, 1)), String::toUpperCase));
        System.out.println("collect2с2fixed = " + collect2с2fixed);

        // 3
        Map<Integer, Integer> map = new HashMap<>();
        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(n -> {
                    if (n == 0) map.put(n, 0);
                    else if (n == 1) map.put(n, 1);
                    else map.computeIfAbsent(n, fn -> map.get(fn - 1) + map.get(fn - 2));
                }
        );
        System.out.println("map = " + map);

        // 3 fixed
        Map<Integer, Long> cache = new HashMap<>();
        cache.put(0,0L);
        cache.put(1,1L);
        System.out.println("fibonacci(cache, 10) = " + fibonacci(cache,10));
        System.out.println("fibonacci(cache, 7) = " + fibonacci(cache,7));

    }

    public static long fibonacci(Map<Integer, Long> cache, int n) {
        return cache.computeIfAbsent(n, integer ->  fibonacci(cache, integer-1) + fibonacci(cache,integer-2));
    }

    // 2.a fixed
    //  Building the comparator, which calls length() method for each compared String pair, and than
    // (having two integers) calls int_1.compareTo(int_2) for deciding which os String is maximal
    // Comparator.comparing() acts like map when calling keyExtractor.apply(val1).compareTo(keyExtractor.apply(val2)):
    // it maps one type (String) to another (int) through the function ( keyExtractor[apply] = () -> String.length())
    // [Function keyExtractor]
    private static Comparator<String> byLength = comparing(String::length);
    private static Comparator<String> byLength2 = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return ((Integer)o1.length()).compareTo(o2.length());
        }
    };


}
