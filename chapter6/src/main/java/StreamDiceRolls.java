import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.summingDouble;

/**
 * @author paul
 */
public class StreamDiceRolls {

    private static final int N = 100000000;
    private static final double fraction = 1.0 / N;

    public void simulate() {

        System.out.println("--- >>> calculate start");
        long start = System.currentTimeMillis();

        Map<Integer,Double> results = IntStream
                .range(0, N)
                .parallel()
                .mapToObj(value -> {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    return random.nextInt(1, 7) + random.nextInt(1, 7);
                })
                // groupingBy generates Map with key = value from stream, and value -
                // result of summing each value hit replaced by fraction
                .collect(groupingBy(side -> side, summingDouble(value -> fraction)));

        long end = System.currentTimeMillis();
        System.out.println("--- >>> calculate end");
        System.out.println("--- >>> total time = " + (end - start));

        results.entrySet().forEach(System.out::println);
    }

}
