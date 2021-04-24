package com.javabom.toby.chapter7.oxm;

import com.javabom.toby.chapter7.resource.BookResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class OxmConfig {

    @Bean
    public Unmarshaller unmarshaller(){
        Jaxb2Marshaller jaxb2Marshaller=new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.javabom.toby.chapter7.oxm");
        return jaxb2Marshaller;
    }

    @Bean
    public BookResourceLoader bookResourceLoader(Unmarshaller unmarshaller){
        return new BookResourceLoader(unmarshaller, new ClassPathResource("book.xml"));
    }
}
