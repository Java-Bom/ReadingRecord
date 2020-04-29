package Chap6_LambdaAndStream.item43;

import org.junit.jupiter.api.DisplayName;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLTransientException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class LambdaInterfaceTest {
    @DisplayName("함수형 인터페이스 상속")
    void name() {
        XY xy = () -> {
            throw new EOFException();
        };
        XYZ xyz = () -> {
        };

        try {
            //()->void throws EOFException
            xy.m();
        } catch (EOFException e) {
            e.printStackTrace();
        }

        //()->void (throws nothing)
        xyz.m();
    }

    @DisplayName("함수형 인터페이스 상속")
    void name2() {
        //given
        D d = (arg) -> {
            List<String> strings = arg;
            return strings;
        };

//        E ea = (arg)->arg;
        E e = new E() {
            @Override
            public List foo(List arg) throws EOFException, SQLTransientException {
                return null;
            }
        };

        try {
            List<String> strings = d.foo(Collections.singletonList("최유성"));
        } catch (SQLTransientException | EOFException err) {
            err.printStackTrace();
        }
    }

    @DisplayName("함수형 인터페이스 상속")
    void name3() {

        //G g = ()->"a";

        G g = new G() {
            @Override
            public <F extends Exception> String m() throws F {
                return null;
            }
        };

    }

    interface X {
        void m() throws IOException;
    }

    interface Y {
        void m() throws EOFException;
    }


    interface Z {
        void m() throws ClassNotFoundException;
    }


    //-=======================================


    interface XY extends X, Y {
    }

    interface XYZ extends X, Y, Z {
    }

    interface A {
        List<String> foo(List<String> arg)
                throws IOException, SQLTransientException;
    }

    interface B {
        List foo(List<String> arg)
                throws EOFException, SQLException, TimeoutException;
    }

    interface C {
        List foo(List arg) throws Exception;
    }

    interface D extends A, B {
    }


    interface E extends A, B, C {
    }

    //-=======================================
    interface G1 {
        <E extends Exception> Object m() throws E;
    }

    interface G2 {
        <F extends Exception> String m() throws Exception;
    }

    interface G extends G1, G2 {
    }
}
