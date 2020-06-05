package Chap12_Serialization.item88;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MutablePeriod {

	private Period period;

	private Date start;

	private Date end;

	public MutablePeriod() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);

			// Serialize a valid Period instance
			out.writeObject(new Period(new Date(), new Date()));

			byte[] ref = {0x71, 0, 0x7e, 0, 5}; // Ref #5
			bos.write(ref); // The start field
			ref[4] = 4; // Ref # 4
			bos.write(ref); // The end field

			// Deserialize Period and "stolen" Date references
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			period = (Period) in.readObject();
			start = (Date) in.readObject();
			end = (Date) in.readObject();
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public static void main(String[] args) {
		MutablePeriod mp = new MutablePeriod();

		Period p = mp.period;
		Date pEnd = mp.end;

		pEnd.setTime(78);
		System.out.println(p);

		pEnd.setTime(68);
		System.out.println(p);

	}


}
