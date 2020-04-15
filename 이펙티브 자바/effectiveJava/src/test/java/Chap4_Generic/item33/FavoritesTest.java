package Chap4_Generic.item33;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/04/05
 */
class FavoritesTest {
    @Test
    void name() {
        Favorites favorites = new Favorites();
        favorites.putFavorite((Class) Integer.class, "Integer인테 String instance로 넘겨 보자!");
        favorites.getFavorite(Integer.class);
    }

    @Test
    @DisplayName("HashSet Raw Type")
    void name2() {
        HashSet<Integer> set = new HashSet<>();
        ((HashSet) set).add("문자열 입니다.");
    }

    @Test
    @DisplayName("HashSet Raw Type")
    void name3() {
        HashSet<Integer> set = new HashSet<>();
        ((HashSet)set).add("문자열 입니다.");
        boolean contains1 = ((HashSet) set).contains("문자열 입니다.");
        assertThat(contains1).isTrue();
        boolean contains = set.contains("문자열 입니다.");
        assertThat(contains).isTrue();
    }

}