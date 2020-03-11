package item18.self2;

/**
 * Created by jyami on 2020/02/22
 */
// wrapper class
public class ModelChangesCounter {
    private final Model model;
    private int changesMade;

    ModelChangesCounter(Model model) {
        this.model = model;
    }

    // The wrapper is intended to count changes,
    // but those changes which are invoked from
    // Controller are just skipped
    public void makeChange() {
        model.makeChange();
        changesMade++;
    }
}
