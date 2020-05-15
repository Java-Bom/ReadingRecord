package Chap11_Concurrency.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ObservableSet 동작 확인 #3
public class Test3 {
	public static void main(String[] args) {
		ObservableSet<Integer> set =
			new ObservableSet<>(new HashSet<>());

		// 코드 79-2 쓸데없이 백그라운드 스레드를 사용하는 관찰자 (423쪽)
		set.addObserver(new SetObserver<Integer>() {
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23) {
					ExecutorService exec =
						Executors.newSingleThreadExecutor();
					try {
						exec.submit(() -> s.removeObserver(this)).get();
					} catch (ExecutionException | InterruptedException ex) {
						throw new AssertionError(ex);
					} finally {
						exec.shutdown();
					}
				}
			}
		});

		for (int i = 0; i < 100; i++)
			set.add(i);
	}
}

