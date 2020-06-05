package Chap7_LambdaAndStream.item50;

import lombok.Getter;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jyami on 2020/05/30
 */
@Getter
public class Period {
    private Date start;
    private Date end;

    public Period(Date start, Date end, CountDownLatch latch, CountDownLatch latch2){
        // start가 end보다 나중일때 exception
        if (start.after(end))
            throw new IllegalArgumentException(start + "가" + end + "보다 늦다");

        latch2.countDown();
        try {
            latch.await(); // 다른 쓰레드에서 공격이 끝날 때까지 걸려있어야 한다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

    @Override
    public String toString() {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
