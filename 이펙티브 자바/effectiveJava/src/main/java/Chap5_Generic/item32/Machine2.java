package Chap5_Generic.item32;

import java.util.List;

/**
 * Created by jyami on 2020/04/05
 */
public class Machine2 {
    private List versions;

    @SuppressWarnings("unchecked")
    public void addVersion(String version) {
        versions.add(version);
    }
}
