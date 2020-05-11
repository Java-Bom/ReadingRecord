package Chap2_GenerateObjectAndDestroy.item6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Item6Test {

    @DisplayName("String 불변객체 테스트")
    @Test
    void string(){
        String a = "Javabom";
        String b = "Javabom";

        assertThat(a).isSameAs(b);
    }

    @DisplayName("String 인스턴스 생성 테스트")
    @Test
    void stringNotEqual(){
        String a = "Javabom";
        String b = new String("Javabom");

        assertThat(a).isNotSameAs(b);
    }

    @DisplayName("keyset은 같은 Map을 바라본다")
    @Test
    void keyset(){
        Map<String, Object> javabom = new HashMap<>();
        javabom.put("Javabom", "Hello");

        Set<String> javabomSet1 = javabom.keySet();
        Set<String> javabomSet2 = javabom.keySet();

        assertThat(javabomSet1).isSameAs(javabomSet2);

    }


}
