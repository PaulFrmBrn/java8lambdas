import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author paul
 */
public class ManualDiceRolls {

    private static final int N = 100000000;
    private final double fraction;
    private final Map<Integer, Double> results;
    private final int numberOfThreads;
    private final ExecutorService executor;
    private final int workPerThread;

    public ManualDiceRolls() {
        fraction = 1.0 / N; // "cost" of each hit of dice throws for N tries
        results = new ConcurrentHashMap<>();
        numberOfThreads = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(numberOfThreads);
        workPerThread = N / numberOfThreads;
    }

    public void simulate() {

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {

            futures.add(executor.submit(() -> {

                ThreadLocalRandom random = ThreadLocalRandom.current();
                for (int j = 0; j < workPerThread; j++) {

                    int key = random.nextInt(1,7) + random.nextInt(1,7);
                    // if there is no value, value  = fraction (1 hit), else  value += fraction
                    results.compute(key, (key1, previous) -> previous == null ? fraction : previous + fraction);

                }

            }));
            System.out.println("was added future #" + i);

        }

        System.out.println("--- >>> calculate start");
        long start = System.currentTimeMillis();
        futures.forEach(future -> {
            try {
                future.get();
                System.out.println("future.get " + future.hashCode());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("ooooooops");
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("--- >>> calculate end");
        System.out.println("--- >>> total time = " + (end - start));

        executor.shutdown();

        results.entrySet().forEach(System.out::println);

    }

    @Override
    public String toString() {
        return "ManualDiceRolls{" +
                "fraction=" + fraction +
                ", results=" + results +
                ", numberOfThreads=" + numberOfThreads +
                ", executor=" + executor +
                ", workPerThread=" + workPerThread +
                '}';
    }
}
