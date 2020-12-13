package com.javabom.toby.chapter3.pattern.템플릿_콜백_패턴;

import java.io.IOException;
import java.io.InputStream;

public class Calculator {
    public Integer calcSum(InputStream in) throws IOException {
        /*
         * 콜백: 메서드에 전달되는 오브젝트. 파라미터지만 값이아니라 특정로직이다.
         * 변하는 부분을 콜백으로 전달한다.
         * - line, value는 컨텍스트 정보를 전달하는 매개변수이다. 콜백은 이 정보들을 참조하여 템플릿에 전달된다.
         */
        LineCallback sumCallBack = (line, value) -> value + Integer.parseInt(line);
        return StreamReaderTemplate.lineReadTemplate(in, sumCallBack, 0);
    }

    /*
        익명클래스로 넣을 때 메모리 저장 시점
     */
    public Integer calcMultiply(InputStream in) throws IOException {
        return StreamReaderTemplate.lineReadTemplate(in, (line, value) -> value * Integer.parseInt(line), 1);
    }
}
