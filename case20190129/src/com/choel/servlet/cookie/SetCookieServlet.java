package com.choel.servlet.cookie;

import com.choel.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SetCookieServlet")
public class SetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String output = null;
        String username = request.getParameter("username");
        if (!StringUtil.validateNull(username)) {
            Cookie cookie1 = new Cookie("username", StringUtil.filterHtml(username));
            cookie1.setMaxAge(24 * 60 * 60 * 30);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            Cookie cookie2 = new Cookie("lastTime", sdf.format(new Date()));
            cookie2.setMaxAge(24 * 60 * 60 * 30);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            output = "本次登录时间与用户名已经写入到Cookie中<br><a href='/servlet/getCookies'>点击查看Cookies</href>";
        } else {
            output = "用户名为空，请从新输入<br><a href='cookie_input.html'>输入用户名</href>";
        }
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>set cookies</title></head>");
        out.println("<body>");
        out.println("<h2>" + output + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
