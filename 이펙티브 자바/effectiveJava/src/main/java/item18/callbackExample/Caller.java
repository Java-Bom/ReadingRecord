package item18.callbackExample;

import lombok.Getter;

/**
 * Created by jyami on 2020/02/24
 */
@Getter
public class Caller {
    private Callee callee;

    public Caller() {
        Callee.CallBack callBack = new Callee.CallBack() {
            @Override
            public void callbackMethod() {
                System.out.println(this);
            }
        };
        this.callee = new Callee(callBack);
    }
}
