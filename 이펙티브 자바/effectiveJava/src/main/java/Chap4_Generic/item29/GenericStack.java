package Chap4_Generic.item29;

public class GenericStack<E> {
    private E[] elements;
    private int size;

    @SuppressWarnings(value = "unchecked")
    public GenericStack() {
        this.elements = (E[]) new Object[10];
    }

    public void push(E obj) {
        elements[size++] = obj;
    }

    public E pop() {
        if (size == 0) {
            throw new IllegalArgumentException("스택에 아무것도 없습니다");
        }
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

}
