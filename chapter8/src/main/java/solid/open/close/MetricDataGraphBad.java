package solid.open.close;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author paul
 */
// closed for extension
public class MetricDataGraphBad {

    public static final String USER_DATA = "USER_DATA";
    public static final String SYSTEM_DATA = "SYSTEM_DATA";
    public static final String IO_DATA = "IO_DATA";

    private final Map<String,List<String>> data = new HashMap<>();

    public void updateUserData(String data) {
        addData(USER_DATA, data);
    }

    public void updateSystemData(String data) {
        addData(SYSTEM_DATA, data);
    }

    public void updateIOData(String data) {
        addData(IO_DATA, data);
    }

    private void addData(String key, String data) {
        List<String> dataList = new ArrayList<>();
        dataList.add(data);
        this.data.merge(key, dataList, (strings, strings2) -> {
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
