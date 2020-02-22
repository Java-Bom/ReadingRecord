package item17;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/02/22
 */
class AlbumTest {

    @Test
    void titleChange() {
        Album album = new Album("앨범타이틀");
        album.getTitle().name = "앨범타이틀 변경";
        assertThat(album.getTitle().name).isEqualTo("앨범타이틀 변경");
    }
}