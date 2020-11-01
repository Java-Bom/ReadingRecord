package Chap9_GeneralProgrammingPrinciple.item62;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUseKey {
    private static final Map<Key, Object> threadLocalMap = new HashMap<>();

    private ThreadLocalUseKey() {
    }

    public static Key getKey() {
        return new Key();
    }

    public static class Key {
        public void set(Key key, Object value) {
            threadLocalMap.put(key, value);
        }

        public Object get(Key key) {
            return threadLocalMap.get(key);
        }
    }
}
