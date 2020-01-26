package item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PhoneNumberTest {

    @Test
    @DisplayName("eqauls만 재정의해서 참을 반환한다.")
    void name() {
        PhoneNumber phoneNumber = new PhoneNumber(1,2,3);
        PhoneNumber phoneNumber1 = new PhoneNumber(1,2,3);

        assertThat(phoneNumber.equals(phoneNumber1)).isTrue();
    }

    @Test
    @DisplayName("eqauls만 재정의해서 hashMap에 넣으면 서로다른값을 반환한다.")
    void name2() {
        PhoneNumber phoneNumber = new PhoneNumber(1,2,3);
        PhoneNumber phoneNumber1 = new PhoneNumber(1,2,3);

        Map<PhoneNumber,String> map = new HashMap<>();


        map.put(phoneNumber,"유성");
        map.put(phoneNumber1,"민형");

        assertThat(phoneNumber.hashCode() == phoneNumber1.hashCode()).isFalse();
        assertThat(map.get(phoneNumber)).isEqualTo("유성");
        assertThat(map.get(phoneNumber1)).isEqualTo("민형");
    }


    @Test
    @DisplayName("hashMap도 재정의해서 hashMap에 넣으면 서로 같은값을 반환한다.")
    void name3() {
        PhoneNumberOverrideHash phoneNumber = new PhoneNumberOverrideHash(1,2,3);
        PhoneNumberOverrideHash phoneNumber2 = new PhoneNumberOverrideHash(1,2,3);

        Map<PhoneNumber,String> map = new HashMap<>();

        map.put(phoneNumber,"유성");
        map.put(phoneNumber2,"민형");

        assertThat(phoneNumber.hashCode()).isEqualTo(phoneNumber2.hashCode());
        assertThat(map.get(phoneNumber)).isEqualTo("민형");
        assertThat(map.get(phoneNumber2)).isEqualTo("민형");
    }


}