package item18.callbackExample;


import lombok.Getter;

/**
 * Created by jyami on 2020/02/24
 */
@Getter
public class Callee {

    private CallBack callback;

    public Callee(CallBack callback) {
        this.callback = callback;
    }

    interface CallBack {
        void callbackMethod();
    }

}
