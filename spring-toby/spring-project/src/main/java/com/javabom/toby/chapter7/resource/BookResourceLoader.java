package com.javabom.toby.chapter7.resource;

import com.javabom.toby.chapter7.oxm.Book;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class BookResourceLoader {
    private final Unmarshaller unmarshaller;
    private final Resource resource;

    public BookResourceLoader(Unmarshaller unmarshaller, Resource resource) {
        this.unmarshaller = unmarshaller;
        this.resource = resource;
    }

    public Book load() throws IOException {
        InputStream stream = resource.getInputStream();

        Source source = new StreamSource(stream);

        return (Book) unmarshaller.unmarshal(source);
    }
}
