package Chap9_GeneralProgrammingPrinciple.item62;

import java.util.HashMap;
import java.util.Map;

/// TODO: 2020/06/13 이 클래스를 래핑하는 Static 클래스 하나 만들어서 RealMy 클래스는 하나만 띄워두고 접근...
public final class RealMyThreadLocal<T> {

    public RealMyThreadLocal() {
    }

    public void set(T value) {
        MyThreadLocalMap.threadLocalMap.put(Thread.currentThread(), value);
    }

    public T get() {
        return (T) MyThreadLocalMap.threadLocalMap.get(Thread.currentThread());
    }

    static class MyThreadLocalMap {
        static Map<Thread, Object> threadLocalMap = new HashMap<>();
    }
}
