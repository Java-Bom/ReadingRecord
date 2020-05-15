package Chap11_Concurrency.item79;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {
	public ObservableSet(Set<E> set) {
		super(set);
	}

	//	// 코드 79-1 잘못된 코드. 동기화 블록 안에서 외계인 메서드를 호출한다. (420쪽)
	//	private final List<SetObserver<E>> observers
	//		= new ArrayList<>();
	//
	//	public void addObserver(SetObserver<E> observer) {
	//		synchronized (observers) {
	//			observers.add(observer);
	//		}
	//	}
	//
	//	public boolean removeObserver(SetObserver<E> observer) {
	//		synchronized (observers) {
	//			return observers.remove(observer);
	//		}
	//	}
	//
	//	//	private void notifyElementAdded(E element) {
	//	//		synchronized(observers) {
	//	//			for (SetObserver<E> observer : observers)
	//	//				observer.added(this, element);
	//	//		}
	//	//	}
	//
	//	// 코드 79-3 외계인 메서드를 동기화 블록 바깥으로 옮겼다. - 열린 호출 (424쪽)
	//	private void notifyElementAdded(E element) {
	//		List<SetObserver<E>> snapshot = null;
	//		synchronized (observers) {
	//			snapshot = new ArrayList<>(observers);
	//		}
	//		for (SetObserver<E> observer : snapshot)
	//			observer.added(this, element);
	//	}

	// 코드 79-4 CopyOnWriteArrayList를 사용해 구현한 스레드 안전하고 관찰 가능한 집합 (425쪽)
	private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();

	public void addObserver(SetObserver<E> observer) {
		observers.add(observer);
	}

	public boolean removeObserver(SetObserver<E> observer) {
		return observers.remove(observer);
	}

	private void notifyElementAdded(E element) {
		for (SetObserver<E> observer : observers)
			observer.added(this, element);
	}

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added)
			notifyElementAdded(element);
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c)
			result |= add(element);  // notifyElementAdded를 호출한다.
		return result;
	}
}

