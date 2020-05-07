package Chap2_GenerateObjectAndDestroy.item3;

import java.io.Serializable;

public class SingletonObject implements Serializable {

    private static final SingletonObject INSTANCE = new SingletonObject();
    //    transient public static final SingletonObject INSTANCE = new SingletonObject();
    private String name = "I'm Singleton Object";

    public static SingletonObject getInstance() {
        return INSTANCE;
    }

    private SingletonObject() {
        System.out.println(">>>>> Constructor Call");
    }

    public Object readResolve() {
        System.out.println(">>>>>>>> ReadResolve Method Call");
        return this.INSTANCE;
    }

    @Override
    public String toString() {
        return "SingletonObject{" +
                "name='" + name + '\'' +
                '}';
    }
}


