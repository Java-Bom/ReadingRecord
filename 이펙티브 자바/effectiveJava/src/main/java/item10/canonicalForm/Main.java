package item10.canonicalForm;

/**
 * Created by jyami on 2020/01/19
 * https://github.com/CleanCodeStudy/ReadingRecord/issues/14
 */
public class Main {
    public static void main(String[] args) {
        // caseInsensitiveString가 원하는 equals를 구현했으나 대칭성에 맞지 않는 예제임. (instanceof String 때문)
        CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(caseInsensitiveString.equals(s));
        System.out.println(s.equals(caseInsensitiveString));
    }
}
