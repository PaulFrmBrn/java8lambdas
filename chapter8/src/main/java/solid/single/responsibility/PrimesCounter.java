package solid.single.responsibility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author paul
 */
public class PrimesCounter {

    // bad
    public List<Integer> count(int upTo) {

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < upTo; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i  % j ==0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                result.add(i);
            }
        }

        return result;
    }

    // better - separated responsibilities for methods
    public List<Integer> countSeparated(int upTo) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < upTo; i++) {
            if (isPrimeSeparated(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isPrimeSeparated(int i) {
        boolean isPrime = true;
        for (int j = 2; j < i; j++) {
            if (i  % j ==0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

    // good - there is no external iteration - all iterations are internal (streams)
    public List<Integer> countStreams(int upTo) {
        return IntStream.range(1,upTo)
                .filter(this::isPrimeStreams)
                .mapToObj(value -> value)
                .collect(Collectors.<Integer>toList());

    }

    private boolean isPrimeStreams(int i) {
        return IntStream.range(2,i).allMatch(x -> (i % x) != 0);
    }

}
