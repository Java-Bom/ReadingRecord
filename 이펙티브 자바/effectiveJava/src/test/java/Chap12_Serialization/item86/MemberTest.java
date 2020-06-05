package Chap12_Serialization.item86;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	void name() {
		Member member = new Member("쟈미", "mor2222@naver.com", 23);
		byte[] serializedMember = new byte[0];
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(member);
				serializedMember = baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(serializedMember));
	}
}