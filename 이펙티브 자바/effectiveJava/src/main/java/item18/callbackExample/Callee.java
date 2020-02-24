package item18.callbackExample;


import lombok.Getter;

/**
 * Created by jyami on 2020/02/24
 */
@Getter
public class Callee {

    interface CallBack{
        void callbackMethod();
    }

    private CallBack callback;

    public Callee(CallBack callback) {
        this.callback = callback;
    }

}
