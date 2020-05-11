package Chap5_Generic.item32;

/**
 * Created by jyami on 2020/04/05
 */
public class DontSave {
    public static void hello(Object... objects){
        objects[0] = 1;
        objects[1] = "String";
    }
}
