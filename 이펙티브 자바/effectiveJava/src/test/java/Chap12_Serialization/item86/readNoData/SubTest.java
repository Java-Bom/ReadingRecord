package Chap12_Serialization.item86.readNoData;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SubTest {
    //        Sub sub = new Sub("3.0.2");
//
//        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SOURCE));
//        outputStream.writeObject(sub);
//        outputStream.close();

    private static final String SOURCE = "./src/test/java/Chap12_Serialization/item86/readNoData/serializeSub.txt";

    @Test
    void name() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SOURCE));

        Sub deserialized = (Sub) objectInputStream.readObject();
        objectInputStream.close();

        assertAll(
                () -> assertThat(deserialized.version).isEqualTo("3.0.2"),
                () -> assertThat(deserialized.defaultVersion).isEqualTo("1.0.0")
        );
    }
}