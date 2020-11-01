package Chap9_GeneralProgrammingPrinciple.item62;

import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal {
    private static final Map<String, Object> threadLocalMap = new HashMap<>();

    private MyThreadLocal() {
    }

    public static void set(String key, Object value) {
        threadLocalMap.put(key, value);
    }

    public static Object get(String key) {
        return threadLocalMap.get(key);
    }
}
