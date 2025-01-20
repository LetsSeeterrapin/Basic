package com.beyond.basic.b3_servletjsp;


import com.beyond.basic.b1_hello.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//서블릿은 사용자의 request를 쉽게 처리하고, 사용자에게 response쉽게 조립해주는 기술.
//서블릿에서는 urlㅁ핑을 메서드 단위가 아닌, 클래스 단위로 지정
@WebServlet("/servlet/rest/get")
public class ServletRestGet extends HttpServlet {

//    HttpSevletRequest는에는 사용자의 요청정보가 담겨있음
//    HttpServletResponse에는 사용자에게 줘야할 응답정보를 담아야함
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("hongildong");
        hello.setEmail("hongildong@naver.com");
//        resp객체에 header, body를 직접 조립해야함
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hello);
//        body를 조립
//        body에는 많은양에 대이터가 담길수 있으므로 buffer를 기본적으로 사용
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush(); // 버퍼를 통해 데이터가 조립되므로, 마지막에는 버퍼를 비워줘야함.

    }
}
