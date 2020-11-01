package Chap6_EnumTypeAndAnnotation.item36;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAuthority {

    public int myAuthor;

    public static void main(String[] args) {
        FileAuthority fileAuthority = new FileAuthority();
        fileAuthority.setAuthorization(Author.READ, Author.WRITE);

        System.out.println(fileAuthority.getAuthorization());
        System.out.println(fileAuthority.checkAuthorization(Author.WRITE));

        fileAuthority.deleteAuthorization(Author.READ);
        System.out.println(fileAuthority.getAuthorization());
        System.out.println(fileAuthority.checkAuthorization(Author.READ));

        System.out.println(fileAuthority.getAuthorization());
    }

    public int setAuthorization(Author... author) {
        for (int i = 0; i < author.length; i++) {
            this.myAuthor = this.myAuthor | author[i].getValue();
        }

        return this.myAuthor;
    }

    public String getAuthorization() {
        return Author.getAuthors(this.myAuthor);
    }

    public void deleteAuthorization(Author author) {
        this.myAuthor = this.myAuthor & ~author.getValue();
    }

    public boolean checkAuthorization(Author author) {
        return (this.myAuthor & author.getValue()) == author.getValue();
    }

    public enum Author {
        READ(1 << 0),
        WRITE(1 << 1),
        DELETE(1 << 2),
        UPLOAD(1 << 3),
        DOWNLOAD(1 << 4);

        final int value;

        Author(int value) {
            this.value = value;
        }

        public static String getAuthors(int target) {
            return Stream.of(values())
                    .filter(author -> author.checkAuthor(target))
                    .map(Author::toString)
                    .collect(Collectors.joining("&"));
        }

        public int getValue() {
            return value;
        }

        private boolean checkAuthor(int target) {
            return (this.value & target) == this.value;
        }
    }
}
