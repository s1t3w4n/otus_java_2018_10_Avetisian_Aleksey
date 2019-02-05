import java.util.*;
import java.lang.ref.SoftReference;

public class MyCache<K, V> implements HwCache<K, V> {

    private Map<K, SoftReference<V>> data = new HashMap<>();
    private HwListener listener;

    public void put(K key, V value) {
        listener.notify(key, value, "put" );
        data.put(key,new SoftReference<>(value));
    }

    public void remove(K key) {
        listener.notify(key, data.get(key).get(), "remove" );
        data.remove(key);
    }

    public V get(K key) {
        Optional<SoftReference<V>> optional = Optional.ofNullable(data.get(key));
        return optional.map(SoftReference::get).orElse(null);
    }

    public void addListener(HwListener listener) {
        this.listener = listener;
    }

    public void removeListener(HwListener listener) {
        listener = null;
    }
}
