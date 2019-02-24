import java.util.*;
import java.lang.ref.SoftReference;

public class MyCache<K, V> implements HwCache<K, V> {

    private Map<K, SoftReference<V>> data = new HashMap<>();
    private List<HwListener<K, V>> listeners = new ArrayList<>();

    private void callListeners(K key, V value, String action) {
        for (HwListener listener : listeners) {
            try {
                listener.notify(key, value, action);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void put(K key, V value) {
        callListeners(key, value, "put");
        data.put(key, new SoftReference<>(value));
    }

    public void remove(K key) {
        callListeners(key,data.get(key).get(), "remove");
        data.remove(key);
    }

    public V get(K key) {
        Optional<SoftReference<V>> optional = Optional.ofNullable(data.get(key));
        V value = optional.map(SoftReference::get).orElse(null);
        callListeners(key, value, "get");
        return value;
    }

    public void addListener(HwListener listener) {
        listeners.add(listener);
    }

    public void removeListener(HwListener listener) {
        listeners.remove(listener);
    }
}
