package Chap4_ClassAndInterface.item18.callbackExample.callbackJava;

/**
 * Created by jyami on 2020/03/04
 */
public class SelfProblemMain {

    public static void main(String[] args) {
        new ObjectExample().timeCallBack();
        new ObjectNotBindExample().timeCallBack();
        // java가 bind가 안되는 구조라서 결국 두 결과값이 똑같다.
    }

}


