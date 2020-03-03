package item14.compare;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
public class Person implements Comparable<Person> {

    private Integer age;
    private String name;

    public void foo() {
        Person a = new Person(1, "a");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public int compareTo(Person o) {
        int result = Integer.compare(age, o.age);
        if (result == 0) {
            result = name.compareTo(o.name);
        }

        return result;
    }
}
