package Chap7_LambdaAndStream.item48;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class ParellelTest {

    private static final String SENTENCE = "Hello, I'm Javabom Member." +
            "Have a Good Day!";

    @DisplayName("메르센소수 파이프라인을 병렬로")
//    @Test
    void mersenne() {
        Parellel.mersenne(); // 아무것도 출력하지 않는다
    }

    @DisplayName("ThreadLocalRandom")
    @Test
    void random1() {
        long start = System.currentTimeMillis();
        ThreadLocalRandom.current()
                .doubles()
                .parallel()
                .limit(10)
                .sorted()
                .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end - start);
        System.out.println("===============");
    }

    @DisplayName("SplittableRandom")
    @Test
    void random2() {
        SplittableRandom splittableRandom = new SplittableRandom();
        long start = System.currentTimeMillis();
        splittableRandom.doubles()
                .parallel()
                .limit(10)
                .sorted()
                .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end - start);
        System.out.println("===============");
    }

    @DisplayName("병렬 reduce")
    @Test
    void reduceParellel() {
        List<Operand> lists = Arrays.asList(new Operand(1), new Operand(2), new Operand(3), new Operand(4), new Operand(5));

        Operand reduce = lists.stream()
                .parallel()
                .reduce(new Operand(0), (op1, op2) -> new Operand(op1.val + op2.val), Operand::add);

        System.out.println(reduce.val);

    }

    @DisplayName("함수형으로 단어의 수를 세는 메서드 구현 - 벙렬스트림의 실패")
    @Test
    void wordCount() {
        String sentence = "Hello, I'm Javabom Member. Thankyou. LOL~! I'm Happy!!";
        Stream<Character> stream = IntStream.range(0, sentence.length())
                .mapToObj(sentence::charAt);
        System.out.println(countWord(stream.parallel()));
    }

    @DisplayName("함수형으로 단어의 수를 세는 메서드 구현 - Spliterator 재정의")
    @Test
    void wordCount_Spliterator() {
        String sentence = "Hello, I'm Javabom Member. Thankyou. LOL~! I'm Happy!!";
        Spliterator<Character> spliterator = new WordCounterSpliterator(sentence);

        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        System.out.println(countWord(stream.parallel()));
    }

    int countWord(Stream<Character> stream) {
        return stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine)
                .getCounter();
    }

    class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter + 1, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }


    class Operand {
        int val;

        public Operand(int val) {
            this.val = val;
        }

        public Operand add(Operand operand) {
            return new Operand(operand.val + this.val);
        }
    }
}

