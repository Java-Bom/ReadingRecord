package Chap4_ClassAndInterface.item15;

import lombok.Setter;

@Setter
public class Subject {

    private String teacher;
    private String name;

    public Subject(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return this.teacher;
    }


}
