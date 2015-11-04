package solid.open.close;

import javafx.util.Pair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author paul
 */
public class Main {

    public static final String USER_DATA = "USER_DATA";
    public static final String SYSTEM_DATA = "SYSTEM_DATA";
    public static final String IO_DATA = "IO_DATA";

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        MetricDataGraphBad badGraph = new MetricDataGraphBad();
        badGraph.updateIOData("IO DATA 1");
        badGraph.updateIOData("IO DATA 2");
        badGraph.updateIOData("IO DATA 3");
        badGraph.updateSystemData("SYSTEM data 1");
        badGraph.updateSystemData("SYSTEM data 2");
        badGraph.updateUserData("USER DATA 1");
        badGraph.updateSystemData("SYSTEM data 3");
        badGraph.updateIOData("IO DATA 5");

        System.out.println("bad io = " + badGraph.getDataByKey(MetricDataGraphBad.IO_DATA));
        System.out.println("bad system =" + badGraph.getDataByKey(MetricDataGraphBad.SYSTEM_DATA));
        System.out.println("bad user = " + badGraph.getDataByKey(MetricDataGraphBad.USER_DATA));

        MetricDataGraphGood goodGraph = new MetricDataGraphGood();
        goodGraph.addData(data -> new Pair<>(IO_DATA,"IO DATA 1"),IO_DATA);
        goodGraph.addData(data -> new Pair<>(IO_DATA,"IO DATA 2"),IO_DATA);
        goodGraph.addData(data -> new Pair<>(IO_DATA,"IO DATA 3"),IO_DATA);
        goodGraph.addData(data -> new Pair<>(SYSTEM_DATA,"SYSTEM data 1"),SYSTEM_DATA);
        goodGraph.addData(data -> new Pair<>(SYSTEM_DATA,"SYSTEM data 1"),SYSTEM_DATA);
        goodGraph.addData(data -> new Pair<>(USER_DATA,"USER DATA 1"),USER_DATA);
        goodGraph.addData(data -> new Pair<>(SYSTEM_DATA,"SYSTEM data 3"),SYSTEM_DATA);
        goodGraph.addData(data -> new Pair<>(IO_DATA,"IO DATA 5"),IO_DATA);

        System.out.println("good io = " + goodGraph.getDataByKey(IO_DATA));
        System.out.println("good system = " + goodGraph.getDataByKey(SYSTEM_DATA));
        System.out.println("good user = " + goodGraph.getDataByKey(USER_DATA));

        // ThreadLocal - good example if the open/close principle

        ThreadLocal<DateFormat> localFormatter = ThreadLocal.withInitial(SimpleDateFormat::new);
        DateFormat dateFormat = localFormatter.get();

        // Same class, closed for modification, opened for extension
        AtomicInteger threadId = new AtomicInteger();
        ThreadLocal<Integer> localId = ThreadLocal.withInitial(threadId::incrementAndGet);
        Integer integer = localId.get();

    }

}
