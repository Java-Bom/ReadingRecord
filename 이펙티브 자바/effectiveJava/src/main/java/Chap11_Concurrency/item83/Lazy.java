package Chap11_Concurrency.item83;

/**
 * Created by jyami on 2020/07/11
 */
public class Lazy {
    private volatile String field;

    private String getField() {
        String result = field;
        if (field != null) { // 첫번째 검사(락 사용안함)
            return field;
        }
        synchronized (this) {
            if (field == null)
                field = "hello";
            return field;
        }
    }

}
