package Chap2_CommonMethodOfObject.item10.lock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계좌테스트")
class UnLockAccountTest {

    @Test
    @DisplayName("동기화 안된 계좌에서 두개에 쓰레드로 출금하면 0원 이하로 출금할 수 도 있다.")
    void name() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);
        AsynchronousAccount account = new AsynchronousAccount();

        account.deposit(1000);

        Thread thread1 = new Thread(job(account, latch));
        Thread thread2 = new Thread(job(account, latch));
        thread1.setName("첫번째 쓰레드");
        thread2.setName("두번째 쓰레드");

        thread1.start();
        thread2.start();

        latch.await();

        assertThat(account.getAmount() < 0).isTrue();
    }

    @Test
    @DisplayName("동기화 된 계좌에서 두개에 쓰레드로 출금하면 0원까지만 출금한다.")
    void name2() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);
        SynchronizedAccount account = new SynchronizedAccount();

        account.deposit(1000);

        Thread thread1 = new Thread(job(account, latch));
        Thread thread2 = new Thread(job(account, latch));
        thread1.setName("첫번째 쓰레드");
        thread2.setName("두번째 쓰레드");

        thread1.start();
        thread2.start();

        latch.await();

        assertThat(account.getAmount()).isEqualTo(0);
    }







    Runnable job(Account account, CountDownLatch latch) {
        return () -> {
            while (account.getAmount() > 0) {
                int money = (int) (Math.random() * 3 + 1) * 100;
                try {
                    account.withdraw(money);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch.countDown();
        };
    }
}
