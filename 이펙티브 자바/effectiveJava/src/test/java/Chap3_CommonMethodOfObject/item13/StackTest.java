package Chap3_CommonMethodOfObject.item13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
public class StackTest {
    @Test
    @DisplayName("Stack이 super.clone 반환")
    void name() {
        Stack stack = new Stack();
        stack.push(new Foo(1));
        stack.push(new Foo(2));
        Stack cloneStack = stack.clone();
        cloneStack.push(new Foo(3));
        assertAll(
                () -> assertThat(stack).isNotEqualTo(cloneStack),
                () -> assertThat(stack.getElements()).isEqualTo(cloneStack.getElements()),
                () -> assertThat(stack.getSize()).isNotEqualTo(cloneStack.getSize())
        );
        System.out.println(stack.getElements());
        System.out.println(cloneStack.getElements());
        System.out.println(stack.toString());
        System.out.println(cloneStack.toString());
    }
}
