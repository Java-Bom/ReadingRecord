package Chap12_Serialization.item85;

import Chap12_Serialization.item85.accept.Accept;
import Chap12_Serialization.item85.reject.Reject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeserializationFilteringTest {
    private ByteArrayInputStream acceptInput;
    private ByteArrayInputStream rejectInput;
    private ByteArrayInputStream unknownInput;

    @BeforeEach
    void setUp() throws Exception {
        Accept accept = new Accept(1);
        Reject reject = new Reject(2);
        Unknown unknown = new Unknown(3);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);

        outputStream.writeObject(accept);
        acceptInput = new ByteArrayInputStream(baos.toByteArray());

        baos = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(baos);

        outputStream.writeObject(reject);
        rejectInput = new ByteArrayInputStream(baos.toByteArray());

        baos = new ByteArrayOutputStream();
        outputStream = new ObjectOutputStream(baos);

        outputStream.writeObject(unknown);
        unknownInput = new ByteArrayInputStream(baos.toByteArray());
    }

    @DisplayName("WhiteList")
    @Test
    void whiteListFilter() throws Exception {
        final ObjectInputStream acceptInputStream = new ObjectInputStream(acceptInput);
        final ObjectInputStream rejectInputSteam = new ObjectInputStream(rejectInput);
        final ObjectInputStream unknownInputStream = new ObjectInputStream(unknownInput);

        /**
         * 역질렬화 필터 정의
         * 만약 역직렬화 대상 객체가 accept 패키지면 허용
         */
        ObjectInputFilter filter = filterInfo -> {
            if (filterInfo.serialClass().getPackageName().contains("Chap12_Serialization.item85.accept")) {
                return ObjectInputFilter.Status.ALLOWED;
            }
            return ObjectInputFilter.Status.REJECTED;
        };

        acceptInputStream.setObjectInputFilter(filter);
        rejectInputSteam.setObjectInputFilter(filter);
        unknownInputStream.setObjectInputFilter(filter);

        Accept accept = (Accept) acceptInputStream.readObject();
        assertThat(accept.value).isEqualTo(1);

        assertThatThrownBy(rejectInputSteam::readObject)
                .isInstanceOf(InvalidClassException.class);

        //accept 패키지 클래스가 아니여서 InvalidClassException throw!
        assertThatThrownBy(unknownInputStream::readObject)
                .isInstanceOf(InvalidClassException.class);
    }

    @DisplayName("BlackList")
    @Test
    void blackListFilter() throws Exception {
        final ObjectInputStream acceptInputStream = new ObjectInputStream(acceptInput);
        final ObjectInputStream rejectInputSteam = new ObjectInputStream(rejectInput);
        final ObjectInputStream unknownInputStream = new ObjectInputStream(unknownInput);

        /**
         * 역질렬화 필터 정의
         * 만약 역직렬화 대상 객체가 reject 패키지면 거부
         */
        ObjectInputFilter filter = filterInfo -> {
            if (filterInfo.serialClass().getPackageName().contains("Chap12_Serialization.item85.reject")) {
                return ObjectInputFilter.Status.REJECTED;
            }
            return ObjectInputFilter.Status.ALLOWED;
        };

        acceptInputStream.setObjectInputFilter(filter);
        rejectInputSteam.setObjectInputFilter(filter);
        unknownInputStream.setObjectInputFilter(filter);

        Accept accept = (Accept) acceptInputStream.readObject();
        assertThat(accept.value).isEqualTo(1);

        assertThatThrownBy(rejectInputSteam::readObject)
                .isInstanceOf(InvalidClassException.class);

        //Unknown은 역직렬화가 된다!
        Unknown unknown = (Unknown) unknownInputStream.readObject();
        assertThat(unknown.value).isEqualTo(3);
    }

    @DisplayName("객체 그래프 필터링")
    @Test
    void depthFilter() throws Exception {
        final A a = new A();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(a);
        ByteArrayInputStream aInputStream = new ByteArrayInputStream(baos.toByteArray());

        final B b = new B();
        final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        final ObjectOutputStream outputStream2 = new ObjectOutputStream(baos2);
        outputStream2.writeObject(b);
        ByteArrayInputStream bInputStream = new ByteArrayInputStream(baos2.toByteArray());

        ObjectInputFilter filter = filterInfo -> {
            if (filterInfo.depth() > 3) {
                return ObjectInputFilter.Status.REJECTED;
            }

            return ObjectInputFilter.Status.ALLOWED;
        };

        ObjectInputStream objectInputStream = new ObjectInputStream(aInputStream);
        objectInputStream.setObjectInputFilter(filter);

        assertThatThrownBy(objectInputStream::readObject)
                .isInstanceOf(InvalidClassException.class);

        objectInputStream = new ObjectInputStream(bInputStream);
        objectInputStream.setObjectInputFilter(filter);

        B deserializeB = (B) objectInputStream.readObject();
        assertThat(deserializeB.b).isEqualTo("hello");
    }

    @DisplayName("배열 filtering")
    @Test
    void name() throws IOException, ClassNotFoundException {
        E e = new E();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(e);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

        ObjectInputFilter filter = filterInfo -> {
            System.out.println(
                    filterInfo
                            .depth());
            return ObjectInputFilter.Status.UNDECIDED;
        };

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream.setObjectInputFilter(filter);

        E deserialize = (E) objectInputStream.readObject();
    }

    @Test
    void name1() {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("./src/test/java/Chap12_Serialization/item86/readNoData/buffer.txt")))) {
            List<String> data = Arrays.asList("asdg", "zxcv", "qwer", "zdft");
            for (String s : data) {
                out.write(s.getBytes());
                out.write(" ".getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class A implements Serializable {
        public int a = 1;
        public B b = new B();
    }

    static class B implements Serializable {
        public String b = "hello";
        public C c = new C();
    }

    static class C implements Serializable {
        public int c = 2;
        public D d = new D();
    }

    static class E implements Serializable {
        //        public F[] arrays = {new F(), new F(), new F()};
        public int[] arrays = {};
    }

    static class F implements Serializable {
        public D d = new D();
    }

    static class D implements Serializable {
        public String d = "world";
    }
}