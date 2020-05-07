package Chap5_Generic.item29;

public class ArrayStack {
    private Object[] elements;
    private int size;

    public ArrayStack() {
        this.elements = new Object[10];
    }

    public void push(Object obj) {
        elements[size++] = obj;
    }

    public Object pop() {
        if (size == 0) {
            throw new IllegalArgumentException("스택에 아무것도 없습니다");
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

}
