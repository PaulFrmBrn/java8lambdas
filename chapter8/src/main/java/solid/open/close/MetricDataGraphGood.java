package solid.open.close;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author paul
 */
// opened for extension -
// API can take any KEY
public class MetricDataGraphGood {

    private final Map<String,List<String>> data = new HashMap<>();

    public void addData(DataSeries values, String data) {
        Pair<String, String> entry = values.addData(data);
        List<String> dataList = new ArrayList<>();
        dataList.add(entry.getValue());
        this.data.merge(entry.getKey(), dataList, (strings, strings2) -> {
            strings.addAll(strings2);
            return strings;
        });

    }

    public List<String> getDataByKey(String keyPrefix) {
        return data.entrySet()
                .stream()
                .filter(entry ->  entry.getKey().startsWith(keyPrefix))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }

}
