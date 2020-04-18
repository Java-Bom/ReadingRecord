package bit;

import org.junit.jupiter.api.Test;

class TextTest {

    @Test
    void name() {
        //given

        //when
        System.out.println(Text.BOLD | Text.ITALIC);
        System.out.println(Text.BOLD | Text.UNDERLINE);
        System.out.println(Text.ITALIC | Text.UNDERLINE);
        //then

    }
}