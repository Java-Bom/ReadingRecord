package Chap4_Generic.item30;

import java.util.HashMap;
import java.util.Map;

public class NotImmutableMap<K, V> {
    private static final NotImmutableMap SINGLETON_MAP = new NotImmutableMap<>(); // 싱글턴객체
    private final Map<K, V> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <K, V> NotImmutableMap<K, V> getSingleton() { // 제네릭
        return (NotImmutableMap<K, V>) SINGLETON_MAP;
    }

    public void put(K key, V value) { // 불변깨짐
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

}
