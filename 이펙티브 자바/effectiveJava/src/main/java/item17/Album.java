package item17;

/**
 * Created by jyami on 2020/02/22
 */
public class Album {
    private final Title title;

    public Album(String title) {
        this.title = new Title(title);
    }

    public Title getTitle() {
        return title;
    }

    static class Title {
        String name;

        public Title(String name) {
            this.name = name;
        }
    }
}
