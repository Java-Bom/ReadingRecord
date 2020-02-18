package item14.hash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HashObjectTest {

    @DisplayName("hashCode 를 이용할 경우 필드간의 우선순위 비교가 불가능해 진다.")
    @Test
    void compareTo() {
        // 두 객체 모두 hash 값이 1024로 같다.
        HashObject hashObject1 = new HashObject(1, 32);
        HashObject hashObject2 = new HashObject(2, 1);
        assertThat(hashObject1.hashCode() == 1024).isEqualTo(hashObject2.hashCode() == 1024);
        assertThat(hashObject1.compareTo(hashObject2)).isEqualTo(0);
    }
}