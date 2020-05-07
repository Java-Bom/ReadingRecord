package Chap2_GenerateObjectAndDestroy.item3;

import org.junit.Test;

import java.io.*;
import java.util.Base64;

import static org.junit.Assert.assertSame;

public class SingletonObjectTest {

    @Test
    public void 직렬화_역직렬화_싱글턴() throws IOException, ClassNotFoundException {
        SingletonObject singletonObject = SingletonObject.getInstance();
        byte[] serializedMember;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(singletonObject);
                // serializedMember -> 직렬화된 member 객체
                serializedMember = baos.toByteArray();
            }
        }
        // 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
//        System.out.println(Base64.getEncoder().encodeToString(serializedMember));
//        System.out.println(singletonObject);


        // 역직렬화
        byte[] deserializedMember = Base64.getDecoder().decode(Base64.getEncoder().encodeToString(serializedMember));
        try (ByteArrayInputStream bais = new ByteArrayInputStream(deserializedMember)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                System.out.println(">>>>>> call ReadObject ");
                Object objectMember = ois.readObject();
                SingletonObject singletonObject1 = (SingletonObject) objectMember;
                assertSame(singletonObject1, singletonObject);
//                System.out.println(singletonObject1);
            }
        }

    }

}
