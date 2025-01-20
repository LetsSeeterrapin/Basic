package com.beyond.basic.b3_servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/jsp/get")
public class ServletJspGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myData","hello world java");
//        src/main/webapp폴더를 찾아가는 것으로 약속
        req.getRequestDispatcher("WEB-INF/views/hello.jsp").forward(req,resp);
    }
}
