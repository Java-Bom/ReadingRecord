package item14.compare;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Person implements Comparable<Person> {

    private Integer age;
    private String name;

    @Override
    public int compareTo(Person o) {
        int result = Integer.compare(age, o.age);
        if (result == 0) {
            result = name.compareTo(o.name);
        }

        return result;
    }
}
