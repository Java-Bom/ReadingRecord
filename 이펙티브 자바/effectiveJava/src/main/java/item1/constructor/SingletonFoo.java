package item1.constructor;

public class SingletonFoo {
    private static SingletonFoo INSTANCE = new SingletonFoo();

    // 정적팩터리메서드 - Singleton 클래스에서 반환 메서드 담당
    public static SingletonFoo getInstance(){
        return INSTANCE;
    }

}
