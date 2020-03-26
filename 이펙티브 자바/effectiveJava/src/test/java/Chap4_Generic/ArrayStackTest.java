package Chap4_Generic;

import Chap4_Generic.item29.ArrayStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayStackTest {

    @DisplayName("Object리스트타입의 스택은 매번 형변환이 필요하다")
    @Test
    void basExample() {
        Bad bad = new Bad("BadExample1");
        ArrayStack arrayStack = new ArrayStack();
        arrayStack.push(bad);

//        Bad findBad = arrayStack.pop(); CompileError!
//        String maybeBad = (String) arrayStack.pop(); RunTimeError!
    }

    class Bad {
        private String value;

        public Bad(String value) {
            this.value = value;
        }
    }

}
