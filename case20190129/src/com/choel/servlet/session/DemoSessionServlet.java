package com.choel.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Enumeration;

@WebServlet(name = "DemoSessionServlet")
public class DemoSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 1;
        } else {
            counter = counter + 1;
        }
        session.setAttribute("counter", counter);
        session.setMaxInactiveInterval(3600);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>session demo servlet</title></head>");
        out.println("<body>");
        out.println("<h1>使用session演示</h1>");
        out.println("你已经访问了" + counter + "次<br>");
        out.println("LastAccessTime:" + session.getLastAccessedTime() + "<br>");
        out.println("格式转换以后的LastAccessTime:" + new Date(session.getLastAccessedTime()) + "<br>");
        out.println("MaxInactiveInterval:" + session.getMaxInactiveInterval() + "秒<br>");
        out.println("使用Session对象的getAttributeNames<br>");
        Enumeration enumer = session.getAttributeNames();
        String name = null;
        while (enumer.hasMoreElements()) {
            name = (String) enumer.nextElement();
            out.print("Session name:" + name + "<br>");
            out.print("Session value:" + session.getAttribute(name) + "<br>");
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
