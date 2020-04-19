package Chap5_EnumTypeAndAnnotation.item36;

public class StyleWithBitField {
    public static final int STYLE_BOLD = 1 << 0; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    public void applyStyles(int styles) {
//        styleWithBitField.applyStyles(STYLE_BOLD | STYLE_UNDERLINE);

    }
}
