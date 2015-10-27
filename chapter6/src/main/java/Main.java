import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, World");

        // 1. exercise
        int squareSum = IntStream.range(0, 10).map(x -> x * x).sum();
        System.out.println("squareSum = " + squareSum);
        // 1.
        int squareSum2 = IntStream.range(0, 10).parallel().map(x -> x * x).sum();
        System.out.println("squareSum2 = " + squareSum2);


        List<Integer> linkedList = IntStream.range(1, 7).boxed().collect(Collectors.toCollection(LinkedList::new));
        List<Integer> arrayList = IntStream.range(1, 7).boxed().collect(Collectors.toCollection(ArrayList::new));


        // 2.exercise
        Integer reduceResult = linkedList.stream().reduce(5, (acc, element) -> acc * element);
        System.out.println("reduceResult = " + reduceResult);
        // 2.
        Integer reduceResult2 = linkedList.stream().parallel().reduce(1, (acc, element) -> acc * element) * 5;
        System.out.println("reduceResult2 = " + reduceResult2);

        // 3. exercise
        Integer slowReduce = linkedList.stream().map(x -> x * x).reduce(0, (acc, element) -> acc + element);
        System.out.println("slowReduce = " + slowReduce);
        // 3.
        Integer fastReduce = arrayList.stream().parallel().map(x -> x * x).reduce(0, (acc, element) -> acc + element);
        System.out.println("fastReduce = " + fastReduce);
        // 3. fixed (mapToInt works with int - not Integer, and sum() is optimized for int)
        Integer fastestReduce = arrayList.stream().parallel().mapToInt(x -> x * x).sum();
        System.out.println("fastestReduce = " + fastestReduce);

        // example 6.3
        StreamDiceRolls streamDiceRolls = new StreamDiceRolls();
        System.out.println("streamDiceRolls start");
        System.out.println(streamDiceRolls);
        streamDiceRolls.simulate();
        System.out.println("streamDiceRolls end");

        // example 6.4
        ManualDiceRolls manualDiceRolls = new ManualDiceRolls();
        System.out.println("manualDiceRolls start");
        System.out.println(manualDiceRolls);
        manualDiceRolls.simulate();
        System.out.println("manualDiceRolls end");


    }

}
