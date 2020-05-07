package Chap4_ClassAndInterface.item18.callbackExample.callbackJava;

/**
 * Created by jyami on 2020/03/07
 */
class ObjectNotBindExample {
    String data = "dummy";

    void timeCallBack(){
        new SetTimeOut.CallBackSetTime() {
            @Override
            public void callback() {
                System.out.println("==== this.data when not using bind() ====");
                System.out.println(this);
                System.out.println(data);
            }
        }.callback();
    }
}
