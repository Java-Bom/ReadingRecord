package Chap10_Exceptions.item71;

import java.util.Optional;

public class Car {

    private Engine engine;

    public void addEngine(Engine engine) {
        this.engine = engine;
    }

    public boolean hasEngine() {
        return engine != null;
    }

    public Point move() {
        return new Point(engine.power());
    }

    public Optional<Point> moveOptional() {
        if (engine == null) {
            return Optional.empty();
        }
        return Optional.of(new Point(engine.power()));
    }

    public Point moveValue() {
        if (engine == null) {
            return new Point(0);
        }
        return new Point(engine.power());
    }
}
