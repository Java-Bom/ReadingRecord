package Chap12_Serialization.item89.readresolve;

import lombok.Getter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

@Getter
public class Period implements Serializable {
    public static final Period INSTANCE = new Period(new Date(0), new Date(0));

    private Date start;
    private Date end;

    public Period(final Date start, final Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

    private Object readResolve() {
        return Period.INSTANCE;
    }
}
