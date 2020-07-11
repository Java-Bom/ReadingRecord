package Chap11_Concurrency.item82;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/10
 */
class SynchronizedMapSampleTest {
    @Test
    void testMap() {

        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Set<String> set = map.keySet();

        map.put("1", "test");
        map.put("2", "test");
        map.put("3", "test");
        map.put("4", "test");

        new Thread(() -> {
            synchronized (set){
                while(true){
                    for (String key : set) {
                        System.out.print(key);
                    }
                    System.out.println();
                }
            }
        }).start();

        new Thread(() -> {
            map.put("0", "test");
        }).start();

    }
}