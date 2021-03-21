package com.javabom.toby.chapter7;

import com.javabom.toby.chapter7.xsd.SqlType;
import com.javabom.toby.chapter7.xsd.Sqlmap;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JaxbTest {

    @Test
    void readSqlmap() throws JAXBException {
        String contextPath = Sqlmap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream resourceAsStream = Sqlmap.class.getResourceAsStream("/sqlmap.xml");
        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(resourceAsStream);

        List<SqlType> sqlList = sqlmap.getSql();

        assertThat(sqlList.size()).isEqualTo(3);
        assertThat(sqlList.get(0).getKey()).isEqualTo("add");
        assertThat(sqlList.get(0).getValue()).isEqualTo("insert");
        assertThat(sqlList.get(1).getKey()).isEqualTo("get");
        assertThat(sqlList.get(1).getValue()).isEqualTo("select");
        assertThat(sqlList.get(2).getKey()).isEqualTo("delete");
        assertThat(sqlList.get(2).getValue()).isEqualTo("delete");

    }
}
