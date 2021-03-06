package Chap5_Generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GenericTest {


    @Test
    @DisplayName("로타입은 뭐든지 다 들어간다.")
    void rowType() {
        List list = new ArrayList();
        list.add("문자열");
        list.add(1);

        String text = (String) list.get(0);
        Integer number = (Integer) list.get(1);

        assertThat(text).isEqualTo("문자열");
        assertThat(number).isEqualTo(1);
    }

    @Test
    @DisplayName("로타입은 뭐든지 다 들어간다.")
    void rowType2() {
        List<String> stringList = new ArrayList();
        unsafeAdd(stringList,new Integer(1));

        assertThatThrownBy(()->stringList.get(0))
                .isInstanceOf(ClassCastException.class);

    }

    private void unsafeAdd(List list,Object o){
        list.add(o);
    }

    @Test
    @DisplayName("Object배열은 런타임에 안전하다.")
    void name() {
        //given

        //when
        Object[] strings = new String[3];
        strings[0] = "들어간다";
        assertThatThrownBy(() -> strings[1] = 1) // -->컴파일 타임에는 잡아주지못한다.
                .isInstanceOf(ArrayStoreException.class);
        //then
    }


    @Test
    @DisplayName("비정적 와일드카드 사용법")
    void name2() {
        //given

        //when
        Set<Object> source = new HashSet<>();
        source.add("최유성");
        source.add(1);
        source.add(2L);

        Set<Object> destination = new HashSet<>();
        destination.add("최유성");

        assertThat(countWithRow(destination, source)).isEqualTo(1);


        //then

    }

    @Test
    @DisplayName("로타입 사용법")
    void name3() {
        //given

        //when
        List<String> strings = new ArrayList<>();
        int test = 0;
        add(strings, test);
        assertThatThrownBy(() -> strings.get(0))
                .isInstanceOf(ClassCastException.class);


        //then

    }

    private void add(List list, Object o) {
        list.add(o);

    }

    private int countWithWildCard(Set<?> destination, Set<?> source) {
        int result = 0;
        for (Object o : source) {
            if (destination.contains(o)) {
                result++;
            }
        }
        return result;
    }

    private int countWithRow(Set destination, Set source) {
        int result = 0;
        for (Object o : source) {
            if (destination.contains(o)) {
                result++;
            }
        }
        return result;
    }


//    private void count(Set<?> destination){
//        destination.add("아무것도 못넣는다");
//    }
}
