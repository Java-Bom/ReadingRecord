package Chap9_GeneralProgrammingPrinciple.item58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CantUseForEach {

    public static void main(String[] args) {
//        destrucitve();
//        transforming();
        parallel();
    }

    public static void destrucitve() {
        List<Wrap> wraps = new ArrayList<>();

        wraps.add(new Wrap(1));
        wraps.add(new Wrap(2));
        wraps.add(new Wrap(3));
        wraps.add(new Wrap(4));
        wraps.add(new Wrap(5));

//        ConcurrentModificationException 발생!
        wraps.removeIf(wrap -> wrap.value == 1);
    }

    public static void transforming() {
        List<Wrap> wraps = new ArrayList<>();

        wraps.add(new Wrap(1));
        wraps.add(new Wrap(2));
        wraps.add(new Wrap(3));
        wraps.add(new Wrap(4));
        wraps.add(new Wrap(5));

        //변경이 안된다!
        for (Wrap wrap : wraps) {
            if (wrap.value == 2) {
                wrap = new Wrap(20);
            }
        }

        System.out.println(wraps);

        //변경이 되는군
        for (int i = 0; i < wraps.size(); i++) {
            if (wraps.get(i).value == 2) {
                wraps.set(i, new Wrap(20));
            }
        }

        System.out.println(wraps);

        int[] integers = new int[5];
        for (int i = 1; i <= 5; i++) {
            integers[i - 1] = i;
        }

        for (int value : integers) {
            if (value == 2) {
                value = 20;
            }
        }

        System.out.println(Arrays.toString(integers));
    }

    public static void parallel() {
        List<Integer> publishers = Arrays.asList(1, 2, 3);
        List<Integer> clients = Arrays.asList(10, 20, 30);

        for (int publisher : publishers) {
            for (int client : clients) {
                System.out.println(String.format("publisher %d : client %d ", publisher, client));
            }
        }

        System.out.println("=================");

        for (Iterator<Integer> p = publishers.iterator(), c = clients.iterator(); p.hasNext() && c.hasNext(); ) {
            System.out.println(String.format("publisher %d : client %d ", p.next(), c.next()));
        }
    }

    static class Wrap {
        int value;

        public Wrap(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
