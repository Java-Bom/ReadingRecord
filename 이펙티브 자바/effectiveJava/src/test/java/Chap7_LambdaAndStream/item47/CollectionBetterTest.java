package Chap7_LambdaAndStream.item47;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

class CollectionBetterTest {

    @Test
    void name() {
        List<String> lists = Arrays.asList("a", "b", "c");
        Iterator<String> iterator = lists.stream()
                .iterator();


        for (String zz : (Iterable<String>) iterator) {

        }

        for (String bb : new JavaBom()) {

        }
    }

    class JavaBom implements Iterable<String> {

        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public void forEach(Consumer<? super String> action) {

        }

        @Override
        public Spliterator<String> spliterator() {
            return null;
        }
    }
}
