package Chap5_EnumTypeAndAnnotation.item34;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

class WeekDayTest {

    @DisplayName("열거 타입은 Comparable을 구현하였고 열거 상수가 선언된 순서로 결정한다.")
    @Test
    void name() {
        WeekDay monday = WeekDay.MONDAY;
        WeekDay monday2 = WeekDay.MONDAY;
        WeekDay wednesday = WeekDay.WEDNESDAY;
        assertThat(monday.compareTo(wednesday)).isEqualTo(-2);
        assertThat(monday.compareTo(monday2)).isEqualTo(0);
    }

    @DisplayName("열거 타입은 Serializable 을 구현하였다")
    @Test
    void name1() throws IOException {
        WeekDay monday = WeekDay.MONDAY;
        byte[] serialized = serialization(monday);
        WeekDay deserialized = deserialization(serialized);
        assertThat(monday).isEqualTo(deserialized);
        assertThat(monday.getValue()).isEqualTo(deserialized.getValue());
        assertThat(monday.getVar()).isNotEqualTo(deserialized.getVar());
    }

    public byte[] serialization(WeekDay weekDay) throws IOException {
        byte[] serialized;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(weekDay);
                serialized = baos.toByteArray();
            }
        }
        return serialized;
    }

    public WeekDay deserialization(byte[] serialized) throws IOException {
        WeekDay deserialized = WeekDay.TUESDAY;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serialized)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object object = ois.readObject();
                deserialized = (WeekDay) object;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return deserialized;
    }
}