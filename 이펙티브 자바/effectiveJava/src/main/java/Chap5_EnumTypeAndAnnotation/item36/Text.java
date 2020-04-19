package Chap5_EnumTypeAndAnnotation.item36;

import java.util.Set;

public class Text {

    public void applyStyles(Set<Style> styles) {
//        applyStyles(EnumSet.of(Style.BOLD, Style.UNDERLINE));
    }

    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
    }
}
