package Chap12_Serialization.item85;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class HashGraphTest {
    @Test
    void name() throws IOException, ClassNotFoundException {
        HashGraph hashGraph = new HashGraph();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(baos);
        outputStream.writeObject(hashGraph);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        ObjectInputFilter filter = filterInfo -> {
            System.out.println(filterInfo.depth());
            return ObjectInputFilter.Status.UNDECIDED;
        };

        objectInputStream.setObjectInputFilter(filter);

        objectInputStream.readObject();
    }
}