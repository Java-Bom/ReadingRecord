package item10.containsJDK;

/**
 * Created by jyami on 2020/01/19
 */
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CaseInsensitiveString){
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        }
        if(o instanceof String){
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }
}
