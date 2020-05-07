package Chap4_ClassAndInterface.item24.anonymous;

/**
 * 익명 클래스를 사용하는 클라이언트는 사용하는 익명 클래스가 상위 타입에서 상속한 멤버 외에는 호출할 수 없다.
 * <p>
 * 모르겠다. 상위 타입에서 상속한 멤버 외에 해당하는 부분이 어떠한 것인지.
 */
public class AnonymousClient {

    public void foo() {

        Animal animal = new Animal() {

            public void foo() {
                System.out.println(super.abstractExist);
                System.out.println(super.interfaceExist);
            }
        };

        System.out.println("상위 타입의 멤버는 사용가능");
        System.out.println(animal.abstractExist);

        System.out.println("인터페이스도 가능");
        System.out.println(animal.interfaceExist);
    }

}







