package Chap12_Serialization.item90;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

class FooTest {

    @DisplayName("직렬화 역직렬화 과정")
    @Test
    void getValue() throws IOException, ClassNotFoundException {
        byte[] serializedFooBytes;
        Foo foo = new Foo("START");

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(foo);
                serializedFooBytes = baos.toByteArray();
            }
        }

        byte[] deserializedFooBytes = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(serializedFooBytes));
        Foo deserializedFoo;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(deserializedFooBytes)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                deserializedFoo = (Foo) objectMember;
            }
        }

//        assertThat(deserializedFoo.getValue()).isEqualTo("FIX");
//        assertThat(deserializedFoo.getValue()).isEqualTo("READ_OBJECT");
        assertThat(deserializedFoo.getValue()).isEqualTo("START");
    }
}