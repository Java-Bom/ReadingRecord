package item18.callbackExample.callbackJava;

/**
 * Created by jyami on 2020/03/07
 */
class ObjectExample {
    String data = "dummy";

    void timeCallBack(){
        new SetTimeOut.CallBackSetTime() {
            @Override
            public void callback() {
                System.out.println("==== this.data when using bind() ====");
                System.out.println(this);
                System.out.println(data);
            }
        }.callback();
    }

    // bind()를 이용해서 저 callback안에서 javaBom 객체가 this에 해당하게 할 수 없다.

}
