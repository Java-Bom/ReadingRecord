package Chap4_ClassAndInterface.item17;

import java.math.BigInteger;

/**
 * Created by jyami on 2020/02/22
 */
public class BigBigInteger extends BigInteger {
    // BigInteger 하위 호환성으로 인한 보안 문제

    private int number;

    public BigBigInteger(String val) {
        super(val);
    }

    @Override
    public BigInteger add(BigInteger val) {
        number = number + val.bitCount(); // 가변상태 변수
        return super.add(val); //  불변상태임
    }
}
