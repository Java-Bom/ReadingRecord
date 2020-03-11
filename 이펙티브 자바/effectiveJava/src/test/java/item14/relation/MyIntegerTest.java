package item14.relation;

import item14.relation.MyInteger.CompareMyInteger;
import item14.relation.MyInteger.RelationalMyInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 정수형 기본타입 필드가 관계연산자를 이용하여 비교를 하는경우 == 연산자는 에서 의도치 않은 결과가 유발될 수 있다.
 */
class MyIntegerTest {

    @DisplayName("compare 로 비교하면 NullPointerException 이 발생한다.")
    @Test
    void compareTo1() {
        CompareMyInteger compareMyInteger1 = new CompareMyInteger();
        CompareMyInteger compareMyInteger2 = new CompareMyInteger();

        assertThatThrownBy(() -> compareMyInteger1.compareTo(compareMyInteger2))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("관계연산자로 비교하면 두 객체의 비교 필드가 null 일때 의도치 않은 결과를 발생 시킬 수 있다.")
    @Test
    void compareTo2() {
        RelationalMyInteger relationalMyInteger1 = new RelationalMyInteger();
        RelationalMyInteger relationalMyInteger2 = new RelationalMyInteger();

        assertThat(relationalMyInteger1.compareTo(relationalMyInteger2)).isEqualTo(0);
    }

}