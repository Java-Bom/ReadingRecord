package com.javabom.toby.chapter7.oxm;

import com.javabom.toby.chapter7.resource.BookResourceLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OxmConfig.class)
class OxmConfigTest {

    @Autowired
    private Unmarshaller unmarshaller;

    @Autowired
    private BookResourceLoader bookResourceLoader;

    @Test
    @DisplayName("jaxb xml 컨버팅 테스트")
    void name() throws IOException {
        //given
        InputStream stream = getClass().getResourceAsStream("/book.xml");
        Source xmlSource = new StreamSource(stream);
        //when
        Book book= (Book) unmarshaller.unmarshal(xmlSource);

        //then
        assertThat(book.getId()).isEqualTo(1L);
        assertThat(book.getName()).isEqualTo("Book1");
    }

    @Test
    void resourceTest()throws IOException {
        Book book = bookResourceLoader.load();

        //then
        assertThat(book.getId()).isEqualTo(1L);
        assertThat(book.getName()).isEqualTo("Book1");
    }
}
