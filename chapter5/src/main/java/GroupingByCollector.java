import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author paul
 */
// TODO very strange choice of generics in class specification
// TODO maybe
// TODO public class GroupingByCollector<T,K,S> implements Collector<T, Map<K ,S> , Map<K ,S>>
// TODO is more appropriate
public class GroupingByCollector<T,K> implements Collector<T, Map<K ,List<T>> , Map<K ,List<T>>> {

    private final Function<T,K> keyMapper;
    private final Function<T,T> valueMapper;
    private final Set<Characteristics> characteristicses;

    public GroupingByCollector(Function<T,K> keyMapper, Function<T,T> valueMapper) {
        this.keyMapper =keyMapper;
        this.valueMapper = valueMapper;
        characteristicses = new HashSet<>();
        characteristicses.add(Characteristics.IDENTITY_FINISH);
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, t) -> {
            K key = keyMapper.apply(t);
            List<T> value = map.get(key);
            if (value == null) {
                value = new ArrayList<>();
            }
            value.add(valueMapper.apply(t));
            map.put(key,value); // TODO can be optimized
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (map1, map2) -> {
            Map<K, List<T>> resultMap = new HashMap<>();
            resultMap.putAll(map1);
            for (Map.Entry<K, List<T>> entry : map2.entrySet()) {
                List<T> value = resultMap.get(entry.getKey());
                if (value != null) { // there is a hit in resultMap
                    value.addAll(entry.getValue()); // adding valeus in resultMap entry
                    resultMap.put(entry.getKey(),value);
                } else { // there is no such element in resultMap
                    resultMap.put(entry.getKey(), entry.getValue()); // put map2.entry in resultMap as is
                }
            }
            return resultMap;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristicses;
    }
}
