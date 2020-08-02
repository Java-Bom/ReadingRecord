package Chap12_Serialization.item88;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public class ObjectInputValidation implements Serializable {

    private Date start;
    private Date end;

    public ObjectInputValidation(final Date start, final Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        // 아래와 같은 코드가 주석처리가 되어 있지 않다고 가정할 때
//        if (start.getTime() > end.getTime()) {
//            throw new IllegalArgumentException();
//        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        objectInputStream.registerValidation(
                () -> {
                    if (start.getTime() > end.getTime()) {
                        throw new IllegalArgumentException();
                    }
                }
                , 0);

        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

}
