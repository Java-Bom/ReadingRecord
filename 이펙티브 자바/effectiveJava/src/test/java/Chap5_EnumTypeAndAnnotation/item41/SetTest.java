package Chap5_EnumTypeAndAnnotation.item41;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class SetTest {

    class Foo {
        private String foo;

        public Foo(final String foo) {
            this.foo = foo;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Foo foo1 = (Foo) o;
            return Objects.equals(foo, foo1.foo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(foo);
        }
    }

    @Test
    void name() {
        //given
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new TreeSet<>();

        //when

        //then
    }

    @Test
    void name1() {
        //given
        List<Foo> list1 = new ArrayList<>();

        //when
        list1.add(new Foo(""));
        list1.add(new Foo(""));

        //then
        assertThat(list1.size()).isEqualTo(2);
    }
}