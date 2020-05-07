package Chap3_CommonMethodOfObject.item14.point.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @DisplayName("구체 클래스인 Point 의 일반 규약을 지킬 수 있다.")
    @Test
    void test() {
        Point point = new Point(1, 3);
        ColorPoint colorPoint = new ColorPoint(new Point(1, 2), 1);

        assertThat(point.compareTo(colorPoint.asPoint())).isEqualTo(1);
        assertThat(colorPoint.asPoint().compareTo(point)).isEqualTo(-1);
    }

}
