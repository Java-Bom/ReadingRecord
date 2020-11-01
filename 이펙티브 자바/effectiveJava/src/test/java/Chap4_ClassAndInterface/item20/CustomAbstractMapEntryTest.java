package Chap4_ClassAndInterface.item20;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

class CustomAbstractMapEntryTest {

    @DisplayName("Map.Entry를 구현한 골격 구현과, 단순 구현")
    @Test
    void name() {
        String key = "a";
        String value = "b";

        //다른 클래스에 CustomAbstractmapEntry를 상속하고 추상 메소드들을 구현하여 사용
        CustomMapEntry<String, String> customMapEntry = new CustomMapEntry<>(key, value);

        //익명 클래스로 CustomAbstractmapEntry의 추상 메소드들을 구현하여 사용
        CustomAbstractMapEntry<String, String> customAbstractMapEntry = new CustomAbstractMapEntry<String, String>(key, value) {
            @Override
            void printKey() {

            }

            @Override
            void printValue() {

            }
        };

        //추상 메소드 구현없이 Map.Entry의 기능을 바로 사용
        AbstractMap.SimpleEntry<String, String> simpleEntry = new AbstractMap.SimpleEntry<>(key, value);
    }
}