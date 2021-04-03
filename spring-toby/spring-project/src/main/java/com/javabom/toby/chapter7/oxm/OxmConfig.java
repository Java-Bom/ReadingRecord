package com.javabom.toby.chapter7.oxm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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


}
