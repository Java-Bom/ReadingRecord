package Chap12_Serialization.item89;

public class ElvisImpersonator {
	//	private static final byte[] serializedForm = {...};

	public static void main(String[] args) {
		// ElvisStealer.impersonator를 초기화한 다음,
		// 진짜 Elvis(즉, Elvis.INSTANCE)를 반환한다.
		Elvis elvis = (Elvis)deserialize(serializedForm);
		Elvis impersonator = ElvisStealer.impersonator;
		elvis.printFavorites();
		impersonator.printFavorites();
	}
}
