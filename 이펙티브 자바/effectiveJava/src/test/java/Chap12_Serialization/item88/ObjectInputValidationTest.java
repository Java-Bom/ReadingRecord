package Chap12_Serialization.item88;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ObjectInputValidationTest {

    @DisplayName("ObjectInputValidation을 이용해서 잘못된 직렬화 값을 필터링할 수 있다.")
    @Test
    void name() throws IOException {
        byte[] serializedValidationBytes;

        //start가 end 보다 더 뒤에 있는 잘못된 객체의 직렬화 바이트가 있다고 가정한다면
        ObjectInputValidation objectInputValidation = new ObjectInputValidation(new Date(2), new Date(1));

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(objectInputValidation);
                serializedValidationBytes = baos.toByteArray();
            }
        }

        byte[] deserializedValidationBytes = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(serializedValidationBytes));

        try (ByteArrayInputStream bais = new ByteArrayInputStream(deserializedValidationBytes)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                // readObject 하는 단계에서 register 한 validation에 걸린다.
                assertThatThrownBy(ois::readObject)
                        .isInstanceOf(IllegalArgumentException.class);

            }
        }
    }
}