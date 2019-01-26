package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8"); // 解决中文乱码
        out.println("<html>");
        out.println("<head><title>display login information</title></head>");
        out.println("<body>");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || username.length() == 0 || password == null || password.length() == 0) {
            out.println("<h2>输入用户名和密码不正确</h2>");
            out.println("<br><a href='"+ request.getContextPath() +"/servlet/login'>请重新登录</a>"); // getContextPath() 获取当前java WEB应用的名称
        } else {
            out.print("<h2>用户输入信息：</h2>");
            out.println("<strong>用户名：" + username + "</strong><br>");
            out.println("<strong>密码：" + password + "</strong><br>");
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
