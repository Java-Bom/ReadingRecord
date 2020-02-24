package item18.callbackExample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/02/24
 */
class CallerTest {

    @Test
    void callerTest() {
        Caller caller = new Caller();
        caller.getCallee().getCallback().callbackMethod();
    }
}