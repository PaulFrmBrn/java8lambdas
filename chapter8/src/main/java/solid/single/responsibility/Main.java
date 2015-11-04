package solid.single.responsibility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        PrimesCounter counterBad = new PrimesCounter();

        // bad
        System.out.println("count = " + counterBad.count(100));

        // better
        System.out.println("countSeparated = " + counterBad.countSeparated(100));

        // good
        System.out.println("countStreams = " + counterBad.countStreams(100));

    }

}
