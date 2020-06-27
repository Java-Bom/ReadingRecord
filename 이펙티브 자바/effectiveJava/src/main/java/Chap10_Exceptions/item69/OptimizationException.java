package Chap10_Exceptions.item69;

public class OptimizationException {

    public void optimizeLoop() {
        Mountain range[] = new Mountain[10];
        try {
            int i = 0;
            while (true) {
                range[i++].climb();
            }
        } catch (Exception e) {
        }
    }

    public void optimizeLoop2() {
        Mountain range[] = new Mountain[10];
        for (Mountain i : range) {
            i.climb();
        }
    }

    private static class Mountain {
        public void climb() {
        }
    }
}
