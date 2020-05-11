package Chap3_CommonMethodOfObject.item14.relation;

public class MyInteger {

    public static class CompareMyInteger implements Comparable<CompareMyInteger> {
        private Integer integer;

        @Override
        public int compareTo(CompareMyInteger compareMyInteger) {
            return Integer.compare(integer, compareMyInteger.integer);
        }
    }

    public static class RelationalMyInteger implements Comparable<RelationalMyInteger> {
        private Integer integer;

        @Override
        public int compareTo(RelationalMyInteger relationalMyInteger) {
            if (integer == relationalMyInteger.integer) {
                return 0;
            }
            if (integer > relationalMyInteger.integer) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
