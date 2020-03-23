package Chap3_ClassAndInterface.item18.self2;

/**
 * Created by jyami on 2020/02/22
 */
public class Controller {
    private Model model;

    public void register(Model model) {
        this.model = model;
    }

    // Here the wrapper just fails to count changes,
    // because it does not know about the wrapped object
    // references leaked
    public void doChanges() {
        model.makeChange();
    }
}
