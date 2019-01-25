package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginFormServlet")
public class LoginFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;content=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>login form page</title></head>");
        out.println("<body>");
        out.println("<h2>请输入登录信息</h2>");
        out.println("<form method='post'> action='" + request.getContextPath() + "/servlet/loginForm'>");
        out.println("<table border='1'><tr><td>");
        out.println("<html>");
        out.println("用户名：</td><td>");
        out.println("<input type='text' name='username' size='20'>");
        out.println("</td></tr><tr><td>");
        out.println("密码：</td><td>");
        out.println("<input type='password' name='password' size='20'>");
        out.println("</td></tr><tr><td>&nbsp;</td>");
        out.println("<td><input type='submit' value='登录'></td></tr>");
        out.print("</table></form>");
        out.print("</body>");
        out.print("/html");
        out.flush();
        out.close();
    }
}
