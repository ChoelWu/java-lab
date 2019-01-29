package com.choel.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCookieServlet")
public class GetCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>display login information</title></head>");
        out.println("<body>");
        out.println("<h2>从Cookie中获得上次登录的时间和用户名</h2>");
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie cookieVal : cookies) {
            cookie = cookieVal;
            if (cookie.getName().equals("username")) {
                out.print("用户名：" + cookie.getValue() + "<br>");
            }
            if (cookie.getName().equals("lastTime")) {
                out.print("上次登录时间：" + cookie.getValue() + "<br>");
            }
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
