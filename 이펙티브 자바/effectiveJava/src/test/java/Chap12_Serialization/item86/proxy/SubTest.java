package Chap12_Serialization.item86.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

class SubTest {

    @DisplayName("프록시 X")
    @Test
    void name() throws Exception {
        Sub sub = new Sub(2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(sub);

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Sub deserialize = (Sub) objectInputStream.readObject();

        assertThat(deserialize.getSubValue()).isEqualTo(2);
    }

    @DisplayName("프록시 O")
    @Test
    void name2() throws Exception {
        ProxySub proxySub = new ProxySub(2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(proxySub);

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        ProxySub deserialize = (ProxySub) objectInputStream.readObject();

        assertThat(deserialize.getSubValue()).isEqualTo(2);
    }
}