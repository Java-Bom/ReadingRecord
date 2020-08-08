package Chap12_Serialization.item87;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CycleDeserializationTest {
    @Test
    void name() throws IOException, ClassNotFoundException {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(a);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        A deserialzedA = (A) objectInputStream.readObject();
        System.out.println(deserialzedA.b);
    }

    private static class A implements Serializable {
        private B b;

        public void setB(final B b) {
            this.b = b;
        }
    }

    private static class B implements Serializable {
        private A a;

        public void setA(final A a) {
            this.a = a;
        }
    }
}
