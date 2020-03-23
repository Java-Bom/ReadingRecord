package Chap3_ClassAndInterface.item24;

public class NestedStaticExample {

    private String name;
    private StaticMemberClass staticMemberClass;

    private static class StaticMemberClass {
        private String name;

        public StaticMemberClass(String name) {
            this.name = name;
            NestedStaticExample nestedStaticExample = new NestedStaticExample();
            nestedStaticExample.name = "outName";
        }
    }

}
