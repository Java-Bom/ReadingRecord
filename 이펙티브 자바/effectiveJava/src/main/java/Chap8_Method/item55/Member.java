package Chap8_Method.item55;

public class Member {
    private Long id;
    private String name;

    public Member(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
