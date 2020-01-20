package item10.infiniteRecursion;

import java.awt.*;

/**
 * Created by jyami on 2020/01/19
 */
public class ColorPoint extends Point {

    private final Color color;


    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point))
            return false;

        if(!(o instanceof ColorPoint))
            return o.equals(this);

        return super.equals(o) && ((ColorPoint) o).color == color;
    }

}
