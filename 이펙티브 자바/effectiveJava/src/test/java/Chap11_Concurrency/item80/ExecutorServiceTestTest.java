package Chap11_Concurrency.item80;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExecutorServiceTestTest {

	private Integer getMyNumber(int result) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("myNumber" + result);
		return result;
	}

	@Test
	@DisplayName("특정 태스크가 완료되기를 기다린다.")
	void test1() throws ExecutionException, InterruptedException {
		// given
		ExecutorService exec = Executors.newSingleThreadExecutor();
		// when
		exec.submit(() -> getMyNumber(1)).get();
		// then
	}

	@Test
	@DisplayName("모든 태스크가 완료되기를 기다린다.")
	void test2() throws InterruptedException {
		// given
		ExecutorService exec = Executors.newSingleThreadExecutor();
		// when

		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int paramValue = i;
			tasks.add(() -> getMyNumber(paramValue));
		}

		// then
		List<Future<Integer>> futures = exec.invokeAll(tasks);
	}

	@Test
	@DisplayName("한개의 태스크라도 완료되면 종료한다.")
	void test3() throws InterruptedException, ExecutionException {
		// given
		ExecutorService exec = Executors.newSingleThreadExecutor();
		// when

		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int paramValue = i;
			tasks.add(() -> getMyNumber(paramValue));
		}

		// then
		Integer integer = exec.invokeAny(tasks);
	}

	@Test
	@DisplayName("실행자 서비스가 종료하기를 기다린다.")
	void test4() throws InterruptedException {
		// given
		ExecutorService exec = Executors.newSingleThreadExecutor();
		// when

		Future<Integer> submit = exec.submit(() -> getMyNumber(1));
		exec.awaitTermination(10, TimeUnit.SECONDS);
		// timeout 값에 실행자 서비스가 살아있는 시간이 결정된다.
		// task가 3초 짜리 여도 10초간 동작 후 테스트 종료

	}

	@Test
	@DisplayName("완료된 태스크의 결과를 차례로 받는다.")
	void test5() throws InterruptedException, ExecutionException {
		final int max = 5;

		ExecutorService executorService = Executors.newFixedThreadPool(max);
		ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(executorService);

		List<Future<Integer>> futures = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			int paramValue = i;
			futures.add(executorCompletionService.submit(() -> getMyNumber(paramValue)));
		}

		for (int i = 0; i < max; i++) {
			Integer integer = executorCompletionService.take().get();
			System.out.println("getMyNumber :" + integer);
		}

		executorService.shutdown();
	}

}