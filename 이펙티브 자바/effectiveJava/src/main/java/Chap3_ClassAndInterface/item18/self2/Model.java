package Chap3_ClassAndInterface.item18.self2;

/**
 * Created by jyami on 2020/02/22
 */
// basic class which we will wrap
public class Model {
    Controller controller;

    Model(Controller controller) {
        this.controller = controller;
        controller.register(this); //Pass SELF reference
    }

    public void makeChange() {
        System.out.println("model makeChange");
    }
}
