package Chap12_Serialization.item86;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

public class Member implements Serializable {
	private String name;
	private String email;
	private int age;

	public Member(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("Member{name='%s', email='%s', age='%s'}", name, email, age);
	}

	public static void main(String[] args) {

	}

	public static void serialization() {
		Member member = new Member("쟈미", "mor2222@naver.com", 23);
		byte[] serializedMember = new byte[0];
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(member);
				serializedMember = baos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Base64.getEncoder().encodeToString(serializedMember));
	}

	public static void deserialization(){
		String base64Member = "rO0ABXNyACJDaGFwMTJfU2VyaWFsaXphdGlvbi5pdGVtODYuTWVtYmVyxLmdjrgbfsACAANJAANhZ2VMAAVlbWFpbHQAEkxqYXZhL2xhbmcvU3RyaW5nO0wABG5hbWVxAH4AAXhwAAAAF3QAEW1vcjIyMjJAbmF2ZXIuY29tdAAG7J+I66+4";
		byte[] serializedMember = Base64.getDecoder().decode(base64Member);
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				// 역직렬화된 Member 객체를 읽어온다.
				Object objectMember = ois.readObject();
				Member member = (Member)objectMember;
				System.out.println(member);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

