package Chap11_Concurrency.item78;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

// 코드 78-1 잘못된 코드 - 이 프로그램은 얼마나 오래 실행될까? (415쪽)
public class StopThread {
	private static boolean stopRequested;

	public static void main(String[] args)
		throws InterruptedException {
		Thread backgroundThread = new Thread(() -> {
			int i = 0;
			while (!stopRequested)
				i++;
		});
		backgroundThread.start();

		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}
}

