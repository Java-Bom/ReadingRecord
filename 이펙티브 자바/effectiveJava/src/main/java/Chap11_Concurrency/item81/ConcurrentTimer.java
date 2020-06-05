package Chap11_Concurrency.item81;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class ConcurrentTimer {
	private ConcurrentTimer() { } // 인스턴스 생성 불가

	public static long time(Executor executor, int concurrency,
		Runnable action) throws InterruptedException {
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done  = new CountDownLatch(concurrency);

		for (int i = 0; i < concurrency; i++) {
			executor.execute(() -> {
				ready.countDown(); // 타이머에게 준비를 마쳤음을 알린다.
				try {
					start.await(); // 모든 작업자 스레드가 준비될 때까지 기다린다.
					action.run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					done.countDown();  // 타이머에게 작업을 마쳤음을 알린다.
				}
			});
		}

		ready.await();     // 모든 작업자가 준비될 때까지 기다린다.
		long startNanos = System.nanoTime();
		start.countDown(); // 작업자들을 깨운다.
		done.await();      // 모든 작업자가 일을 끝마치기를 기다린다.
		return System.nanoTime() - startNanos;
	}

}

