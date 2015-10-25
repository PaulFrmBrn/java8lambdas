import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @author paul
 */
public class GroupingByCollectorFixed<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final static Set<Collector.Characteristics> characteristics = new HashSet<>();
    static {
        characteristics.add(Collector.Characteristics.IDENTITY_FINISH);
    }

    private final Function<T,K> keyMapper;
    private final Function<T,T> valueMapper;

    public GroupingByCollectorFixed(Function<T,K> keyMapper, Function<T,T> valueMapper) {
        this.keyMapper =keyMapper;
        this.valueMapper = valueMapper;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, t) -> {
            K key = keyMapper.apply(t);
            List<T> value = map.computeIfAbsent(key, k -> new ArrayList<>());
            value.add(valueMapper.apply(t));
        };
    }
    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (left, right) -> {
            right.forEach((key, value) -> left.merge(key, value, (leftValue, rightValue) -> {
                leftValue.addAll(rightValue);
                return leftValue;
            }));
            return left;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
