package item14.compare;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;


class CompareTest {

    @Test
    @DisplayName("Comparable이 구현되어 있다면 TreeCollection은 오름차순 정렬한다.")
    void name() {
        Person person = new Person(28, "유성");
        Person person2 = new Person(28, "찬인");
        Person person4 = new Person(27, "민형");
        Person person3 = new Person(26, "재연");
        Person person5 = new Person(24, "민정");

        Set<Person> treeSet = new TreeSet<>();
        treeSet.add(person);
        treeSet.add(person2);
        treeSet.add(person4);
        treeSet.add(person3);
        treeSet.add(person5);

        System.out.println(treeSet);
        Object[] sortedArray = treeSet.toArray();

        assertThat(getNameAfterConvert(sortedArray[0])).isEqualTo("민정");
        assertThat(getNameAfterConvert(sortedArray[1])).isEqualTo("재연");
        assertThat(getNameAfterConvert(sortedArray[2])).isEqualTo("민형");
        assertThat(getNameAfterConvert(sortedArray[3])).isEqualTo("유성");
        assertThat(getNameAfterConvert(sortedArray[4])).isEqualTo("찬인");
    }

    @Test
    @DisplayName("treeSet과 HashSet 동치성 검사")
    void name2() {
        Set<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person(28, "찬인"));
        treeSet.add(new Person(28, "유성"));

        assertThat(treeSet.size()).isEqualTo(2);

        Set<Person> hashSet = new HashSet<>();
        hashSet.add(new Person(28, "찬인"));
        hashSet.add(new Person(28, "유성"));

        assertThat(hashSet.size()).isEqualTo(1);

    }


    public String getNameAfterConvert(Object o) {
        return ((Person) o).getName();
    }
}