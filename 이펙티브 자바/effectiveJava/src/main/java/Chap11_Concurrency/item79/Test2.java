package Chap11_Concurrency.item79;

import java.util.HashSet;

// ObservableSet 동작 확인 #2 - 정숫값이 23이면 자신의 구독을 해지한다. (422쪽)
public class Test2 {
	public static void main(String[] args) {
		ObservableSet<Integer> set =
			new ObservableSet<>(new HashSet<>());

		set.addObserver(new SetObserver<Integer>() {
			@Override
			public void added(ObservableSet<Integer> s, Integer e) {
				System.out.println(e);
				if (e == 23) // 값이 23이면 자신을 구독해지한다.
					s.removeObserver(this);
			}
		});

		for (int i = 0; i < 100; i++)
			set.add(i);
	}
}
