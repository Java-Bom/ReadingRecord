package item12;

import java.math.BigInteger;

public class PhoneNumberOverrideToString extends PhoneNumber {


    public PhoneNumberOverrideToString(int areaCode, int prefix, int lineNum) {
        super(areaCode, prefix, lineNum);
    }

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d",areaCode,prefix,lineNum);
    }
}
