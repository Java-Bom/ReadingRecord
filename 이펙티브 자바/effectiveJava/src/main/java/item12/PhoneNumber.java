package item12;

public class PhoneNumber {
    short areaCode;
    short prefix;
    short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNum = (short) lineNum;
    }
}
