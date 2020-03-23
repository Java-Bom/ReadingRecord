package Chap3_ClassAndInterface.item15;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Subject subject = new Subject("seojaeyeon");
    private String id;
    private String password;
    private String name;

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getTeacher() {
        return subject.getTeacher();
    }

    public Subject getSubject() {
        return subject;
    }
}
