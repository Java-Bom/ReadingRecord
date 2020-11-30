package com.javabom.toby.chapter3.pattern.템플릿_콜백_패턴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class Calculator {
    public Integer calcSum(InputStream in) throws IOException {
        /*
         * 콜백: 메서드에 전달되는 오브젝트. 파라미터지만 값이아니라 특정로직이다.
         * 변하는 부분을 콜백으로 전달한다.
         * - line, value는 컨텍스트 정보를 전달하는 매개변수이다. 콜백은 이 정보들을 참조하여 템플릿에 전달된다.
         */
        LineCallback sumCallBack = (line, value) -> value + Integer.parseInt(line);
        return lineReadTemplate(in, sumCallBack, 0);
    }

    public Integer calcMultiplay(InputStream in) throws IOException {
        LineCallback sumCallBack = (line, value) -> value * Integer.parseInt(line);
        return lineReadTemplate(in, sumCallBack, 1);
    }

    /**
     * 템플릿: Calculator 에서 행해지는 연산들에 공통으로 필요한 코드를 템플릿으로 정의한다.
     * 여기서는 InputStream 에서 한줄씩 읽으며 try-catch-finally 로 묶인 일련의 과정이 속한다.
     */
    private Integer lineReadTemplate(InputStream in, LineCallback lineCallback, int initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in));
            Integer res = initVal;
            String line;
            while ((line = br.readLine()) != null) {
                res = lineCallback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (Objects.nonNull(br)) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
