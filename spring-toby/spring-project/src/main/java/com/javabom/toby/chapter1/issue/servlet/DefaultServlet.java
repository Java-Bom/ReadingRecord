package com.javabom.toby.chapter1.issue.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 개발자가 서블릿을 구현하고 서버에 배포할 수는 있지만 그 동작을 제어할 수는 없다.
 * 톰캣과 같은 컨테이너의 설정 파일에 서블릿 설정과 매핑정보를 기입하면
 * 톰캣이 그 설정 정보를 가지고 적절한 시점에 Servlet을 생성하며 요청이 들어올 때마다 생성된 서블릿을 통해 처리한다.
 */
public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost!");
    }
}
