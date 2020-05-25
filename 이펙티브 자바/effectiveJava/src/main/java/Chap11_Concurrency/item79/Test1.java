package Chap11_Concurrency.item79;

import java.util.HashSet;

// ObservableSet 동작 확인 #1 - 0부터 99까지 출력한다. (422쪽)
public class Test1 {
	public static void main(String[] args) {
		ObservableSet<Integer> set =
			new ObservableSet<>(new HashSet<>());

//		set.addObserver(new SetObserver<Integer>() {
//			@Override
//			public void added(ObservableSet<Integer> set, Integer element) {
//				System.out.println(element);
//			}
//		});

		set.addObserver((set1, element) -> System.out.println(element));

		for (int i = 0; i < 100; i++)
			set.add(i);
	}
}
