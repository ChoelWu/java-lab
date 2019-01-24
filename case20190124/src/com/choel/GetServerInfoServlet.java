package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetServerInfoServlet")
public class GetServerInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        printValues(request, out);
        out.flush();
        out.close();
    }

    public void printValues(HttpServletRequest req, PrintWriter out) {
        out.print("服务器IP");
        out.println(req.getRemoteAddr());
        out.print("服务器名");
        out.println(req.getServerName());
        out.print("服务器端口号");
        out.println(req.getServerPort());
        out.print("协议名");
        out.println(req.getScheme());
        out.print("协议版本");
        out.println(req.getProtocol());
        out.print("请求的方法");
        out.println(req.getMethod());
        out.print("请求的URL");
        out.println(req.getRequestURI());
        out.print("上下文路径");
        out.println(req.getContextPath());
        out.print("Servlet 路径");
        out.println(req.getServletPath());
    }
}
