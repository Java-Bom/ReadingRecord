package chapter3.item21;

import java.util.ArrayList;
import java.util.List;

interface Hard {
}

class BoxMaterial implements Hard {
}

class Paper extends BoxMaterial {
}

class Plastic extends BoxMaterial {
}

public class AAA<M extends BoxMaterial & Hard> {

    private M material;

    public static void main(String[] args) {

        AAA<Paper> paperBox = new AAA<>();
        AAA<Plastic> plasticBox = new AAA<>();

        List<Number> a = new ArrayList<>();
        List<Object> b = new ArrayList<>();

        a.add(1);
    }
}
