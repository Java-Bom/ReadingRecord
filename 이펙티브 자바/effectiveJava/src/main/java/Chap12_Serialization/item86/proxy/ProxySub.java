package Chap12_Serialization.item86.proxy;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ProxySub extends Super implements Serializable {
    private int subValue;

    public ProxySub(final int subValue) {
        super(subValue - 1);
        this.subValue = subValue;
    }

    public int getSubValue() {
        return subValue;
    }

    //직렬화 하기전 바깥 클래스를 내부 직렬화 프록시 클래스로 변환
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 12341515436436L;
        private int subValue;

        public SerializationProxy(ProxySub proxySub) {
            this.subValue = proxySub.subValue;
        }

        //역직렬화 시 바깥 클래스로 반환
        private Object readResolve() {
            return new ProxySub(subValue);
        }
    }
}
