package Chap2_CommonMethodOfObject.item11;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class MultiThreadJob {

    public MultiThreadJob(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    public long getCount() {
        return latch.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiThreadJob account = (MultiThreadJob) o;
        return Objects.equals(name, account.name);
    }

    private String name;
    private CountDownLatch latch;
    private int hashCode;


    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            try {
                Thread.sleep(1000L);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hashCode = Objects.hash(name);
            result = hashCode;
        }
        return result;
    }
}
