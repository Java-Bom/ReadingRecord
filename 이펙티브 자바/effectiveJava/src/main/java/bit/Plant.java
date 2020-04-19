package bit;

public class Plant {
    final LifeCycle lifeCycle;

    public Plant(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    enum LifeCycle {ANNUAL, PERENNIAL}
}
