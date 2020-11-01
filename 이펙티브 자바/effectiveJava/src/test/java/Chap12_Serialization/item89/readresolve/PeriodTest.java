package Chap12_Serialization.item89.readresolve;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

    @DisplayName("readResolve가 readObject를 덮어 쓰는 결과를 내놓는다.")
    @Test
    void name() throws IOException, ClassNotFoundException {
        byte[] serializedPeriodBytes;
        Period period = new Period(new Date(1), new Date(2));

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(period);

                serializedPeriodBytes = baos.toByteArray();
            }
        }

        byte[] deserializedPeriodBytes = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(serializedPeriodBytes));
        Period deserializedPeriod;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(deserializedPeriodBytes)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                deserializedPeriod = (Period) objectMember;

            }
        }

        assertThat(deserializedPeriod).isNotEqualTo(period);
        assertThat(deserializedPeriod).isEqualTo(Period.INSTANCE);
    }
}