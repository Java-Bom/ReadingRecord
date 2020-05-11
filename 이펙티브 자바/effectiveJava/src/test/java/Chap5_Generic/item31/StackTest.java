package Chap5_Generic.item31;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StackTest {

    @DisplayName("Number Stack에 Integer를 넣을 수 있는지")
    @Test
    void stacktest() {
        Stack<Number> stack = new Stack<>();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        stack.pushAll(intList);
    }

    @DisplayName("popAll 은 같은 타입의 컬렉션에만 담을 수 있다")
    @Test
    void popAll() {
        Stack<Number> stack = new Stack<>();
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        stack.pushAll(intList);

        List<Object> objStore = new ArrayList<>();
        List<Number> store = new ArrayList<>();
//        stack.popAll(store);
        stack.popAll(objStore);

//        assertThat(store).contains(1,2,3,4);
        assertThat(objStore).contains(1, 2, 3, 4);
    }

    @DisplayName("반환타입에 한정적 와일드타입이 있으면 비검사오류나 캐스팅이 필요하다")
    @Test
    void wild() {
        List<Integer> integers = Arrays.asList(4, 3, 2, 1);

        // 첫번째, 비검사오류
        @SuppressWarnings("unchecked")
        List<Integer> numbers = (List<Integer>) store(integers); // 반환값이 Integer의 하위타입 무엇이든 될 수 있음

        // 두번째, 클라이언트에서 한정적 와일드타입을 사용해야함 -------> 클라이언트가 한정적 와일드타입을 신경쓰는 것 부터 문제.
        List<? extends Integer> numbers2 = store(integers);
        Integer getOne = numbers2.get(1);

        // 반환 타입을 E로 제한하면
        List<Integer> withOutWild = storeWithoutWild(integers); // 어떤 문제도 나타나지 않는다.
    }

    public <E> List<? extends E> store(List<? extends E> list) {
        return new ArrayList<>(list);
    }

    public <E> List<E> storeWithoutWild(List<? extends E> list) {
        return new ArrayList<>(list);
    }

}
