package item10.canonicalForm;

/**
 * Created by jyami on 2020/01/19
 * 참고 : https://stackoverflow.com/questions/33992000/canonical-form-of-field
 * 하지만 나라면 이렇게 짤 것 같음..!
 */
public class CaseInsensitiveString {
    // final 이 붙어서 불변 필드인 상태임. 불변 객체 취급
    private final String s;
    private final String canonicalForm;

    public CaseInsensitiveString(String s) {
        this.s = s;
        this.canonicalForm = s.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return canonicalForm.equals(((CaseInsensitiveString) o).canonicalForm);
        if (o instanceof String) // One-way interoperability!
            return canonicalForm.equals((String) o);
        return false;
    }
}