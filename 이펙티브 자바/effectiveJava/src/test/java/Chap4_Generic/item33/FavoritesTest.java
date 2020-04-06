package Chap4_Generic.item33;

import org.junit.jupiter.api.Test;

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
}