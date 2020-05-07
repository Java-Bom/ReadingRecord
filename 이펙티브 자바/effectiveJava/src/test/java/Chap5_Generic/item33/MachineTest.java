package Chap5_Generic.item33;

import org.junit.jupiter.api.Test;

/**
 * Created by jyami on 2020/04/05
 */
class MachineTest {

    public final <T> void varargsTest(T... varargs) {
        T[] varargs1 = varargs;

    }

    public final <T> T[] varargsArray(T... varargs) {
        return varargs;
    }



    @Test
    void name() {

    }
}
