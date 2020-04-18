package bit;

public class Text {
    public static final int BOLD = 1 << 0;
    public static final int ITALIC = 1 << 1;
    public static final int UNDERLINE = 1 << 2;
    public static final int STRIKETHROUGH = 1 << 3;

    private int style;

    public void applyStyle(int[] styles) {
        int tempStyle = styles[0];

        for (int i = 1; i < styles.length; i++) {
            tempStyle = tempStyle | styles[i];
        }
    }
}
