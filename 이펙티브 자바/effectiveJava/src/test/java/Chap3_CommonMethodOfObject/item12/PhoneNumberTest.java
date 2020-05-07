package Chap3_CommonMethodOfObject.item12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberTest {

    @Test
    @DisplayName("재정의 하지않은 toString은 class명@16진수해쉬값 의 형태를 보인다.")
    public void name() {
        PhoneNumber ph = new PhoneNumber(010,123,1234);
        PhoneNumberOverrideToString phStr = new PhoneNumberOverrideToString(010,123,1234);

        System.out.println(ph);
        System.out.println(phStr);

    }

    @Test
    @DisplayName("Collection")
    void name2() {
        List<PhoneNumberOverrideToString> phones = new ArrayList<>();
        phones.add(new PhoneNumberOverrideToString(010,123,1234));
        phones.add(new PhoneNumberOverrideToString(010,456,1234));

        System.out.println(phones);
    }
}
