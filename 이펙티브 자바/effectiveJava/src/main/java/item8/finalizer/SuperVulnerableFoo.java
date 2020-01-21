package item8.finalizer;

import lombok.Getter;
import lombok.ToString;

public class SuperVulnerableFoo {
    private int value = 0;

    // finalize Attack 을 피하는 방법.
//    @Override
//    protected final void finalize() throws Throwable {
//        super.finalize();
//    }

    public SuperVulnerableFoo(int value) {
        if(value < 0){
            throw new IllegalArgumentException("음수가 들어올 수 없습니다!");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
