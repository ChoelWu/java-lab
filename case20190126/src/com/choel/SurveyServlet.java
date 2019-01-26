package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SurveyServlet")
public class SurveyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        out.println("<html>");
        out.println("<head><title>display survey information</title><head>");
        out.println("<body>");
        out.println("<h2>用户输入信息：</h2>");
        out.println("<strong>用户名：" + filter(request.getParameter("name")) + "</strong><br>");
        out.println("<strong>email：" + filter(request.getParameter("email")) + "</strong><br>");
        out.println("<strong>年纪：" + request.getParameter("age") + "</strong><br>");
        out.println("<strong>编程时间：" + request.getParameter("codetime") + "</strong><br>");
        out.println("<strong>使用的操作系统：</strong>");
        printValues(out, request.getParameterValues("os")); // getParameter() 单值表单的值   getParameterValues() 获得多值表单的值
        out.println("<strong>使用的编程语言：</strong>");
        printValues(out, request.getParameterValues("language"));
        out.println("<strong>建议：" + filter(request.getParameter("comment")) + "</strong><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("get");
    }

    private String filter(String input) {
        if (input == null) {
            return null;
        }
        if (input.length() == 0) {
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll("\n", "<br>");
        return input;
    }

    private void printValues(PrintWriter out, String[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        out.println("<ul>");
        for (int i = 0; i < values.length; i++) {
            out.println("<li>" + values[i] + "</li>");
        }
        out.println("</ul>");
    }
}
