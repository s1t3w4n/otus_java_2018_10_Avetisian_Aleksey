import java.util.*;
import java.lang.ref.SoftReference;

public class MyCache<K, V> implements HwCache<K, V> {

    private Map<K, SoftReference<V>> data = new HashMap<>();
    private List<HwListener<K, V>> listeners = new ArrayList<>();

    public void put(K key, V value) {
        for (HwListener listener : listeners) {
            //prevent unexpected stop
            try {
                listener.notify(key, value, "put");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data.put(key, new SoftReference<>(value));
    }

    public void remove(K key) {
        for (HwListener listener : listeners) {
            //prevent unexpected stop
            try {
                listener.notify(key, data.get(key).get(), "remove");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data.remove(key);
    }

    public V get(K key) {
        Optional<SoftReference<V>> optional = Optional.ofNullable(data.get(key));
        return optional.map(SoftReference::get).orElse(null);
    }

    public void addListener(HwListener listener) {
        listeners.add(listener);
    }

    public void removeListener(HwListener listener) {
        listeners.remove(listener);
    }
}
