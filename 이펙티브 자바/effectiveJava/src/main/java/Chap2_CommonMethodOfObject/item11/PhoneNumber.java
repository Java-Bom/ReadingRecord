package Chap2_CommonMethodOfObject.item11;

public class PhoneNumber {

    int areaCode;
    int prefix;
    int lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber address = (PhoneNumber) o;
        return areaCode == address.areaCode &&
                prefix == address.prefix &&
                lineNum == address.lineNum;
    }

}
