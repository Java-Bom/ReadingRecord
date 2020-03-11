package item19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;


class SerializableSubFooTest {


    @DisplayName("역직렬화시 readObject가 재정의된 메소드 호출")
    @Test
    void name() throws IOException, ClassNotFoundException{
        SerializableSubFoo subFoo = new SerializableSubFoo();
        subFoo.setStr("hi!!!!");

        //직렬화
        byte[] serializedFoo;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(subFoo);
                serializedFoo = baos.toByteArray();
            }
        }

        // 역직렬화
        assertThatThrownBy(() -> {
            byte[] deserializedMember = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(serializedFoo));
            try (ByteArrayInputStream bais = new ByteArrayInputStream(deserializedMember)) {
                try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                    Object objectMember = ois.readObject();
                    SerializableSubFoo deserialized = (SerializableSubFoo) objectMember;
                    assertSame(deserialized,subFoo);
                }
            }
        }).isInstanceOf(NullPointerException.class);
    }
}