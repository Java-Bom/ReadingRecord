package Chap12_Serialization.item88;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MutablePeriod {

    public final Period period;

    public final Date start;

    public final Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            out.writeObject(new Period(new Date(), new Date()));

            //
            //byte코드로 Date 참조를 bos에서 교체
            //

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));

            period = (Period) in.readObject();
            start = (Date) in.readObject();
            end = (Date) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError();
        }
    }
}
