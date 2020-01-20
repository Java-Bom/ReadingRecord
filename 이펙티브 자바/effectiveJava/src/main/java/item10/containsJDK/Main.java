package item10.containsJDK;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/01/19
 * https://github.com/CleanCodeStudy/ReadingRecord/issues/13 답변을 위한 실험
 */
public class Main {
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        List<CaseInsensitiveString> list = new ArrayList<CaseInsensitiveString>();
        list.add(cis);
        boolean bool = list.contains(s);
        System.out.println(bool);

    }
}
