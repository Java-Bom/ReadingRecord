package Chap5_Generic.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Stack<E> {
    private List<E> list = new ArrayList<>();

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void push(E e) {
        list.add(e);
    }

    public void popAll(Collection<? super E> dst) {
        dst.addAll(list);
        list.clear();
    }
}
