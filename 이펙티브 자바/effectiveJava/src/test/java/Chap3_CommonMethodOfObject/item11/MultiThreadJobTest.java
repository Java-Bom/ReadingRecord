package Chap3_CommonMethodOfObject.item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MultiThreadJobTest {

    @DisplayName("지연 초기화시 다수의 스레드가 동시에 접근하면 초기화 로직을 여러 쓰레드가 수행할 수 있다")
    @Test
    void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        MultiThreadJob job = new MultiThreadJob("processor", latch);

        new Thread(job::hashCode).start();
        new Thread(job::hashCode).start();
        new Thread(job::hashCode).start();
        new Thread(job::hashCode).start();
        new Thread(job::hashCode).start();

        latch.await();

        assertThat(job.getCount()).isEqualTo(0);
    }
}
