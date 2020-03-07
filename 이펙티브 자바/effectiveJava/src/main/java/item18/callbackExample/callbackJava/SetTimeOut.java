package item18.callbackExample.callbackJava;

/**
 * Created by jyami on 2020/03/07
 */
public class SetTimeOut {

    public void setTimeout(CallBackSetTime callBackSetTime){
        callBackSetTime.callback();
    }

    public interface CallBackSetTime {
        void callback();
    }

}
