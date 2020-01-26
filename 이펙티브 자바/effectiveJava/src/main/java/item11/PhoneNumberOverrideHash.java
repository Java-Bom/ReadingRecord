package item11;

import java.util.Objects;

public class PhoneNumberOverrideHash extends PhoneNumber {

    public PhoneNumberOverrideHash(int areaCode, int prefix, int lineNum) {
        super(areaCode, prefix, lineNum);
    }

        @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNum);
    }

}
