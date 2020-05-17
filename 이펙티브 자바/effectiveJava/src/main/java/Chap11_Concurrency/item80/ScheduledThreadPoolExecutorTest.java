package Chap11_Concurrency.item80;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
	public static void main(String[] args) {
		// 태스크를 특정 시간에 혹은 주기적으로 실행하게 한다.
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		executor.scheduleAtFixedRate(() -> {
			System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
		}, 0, 2, TimeUnit.SECONDS);
	}
}
