package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "Survey2Servlet")
public class Survey2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        out.println("<html>");
        out.println("<head><title>display survey information</title><head>");
        out.println("<body>");
        out.println("<h2>用户输入信息：</h2>");
        String parameterName = null;
        Enumeration e = request.getParameterNames(); // 在事先不知道有哪些参数的情况下获取枚举类型的参数
        while (e.hasMoreElements()) {
            parameterName = (String) e.nextElement();
            out.println("参数名：" + parameterName + "<br>");
            printValues(out, request.getParameterValues(parameterName));
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void printValues(PrintWriter out, String[] values) {
        out.println("<ul>");
        for (int i = 0; i < values.length; i++) {
            out.println("<li>" + values[i] + "</li>");
        }
        out.println("</ul>");
    }
}
