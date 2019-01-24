package com.choel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "GetHeaderInfoServlet")
public class GetHeaderInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printHeader(request, response);
    }

    public void printHeader(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String headers = null;
        Enumeration e = request.getHeaderNames();
        response.setContentType("text/html;content=utf-8");
        PrintWriter out = response.getWriter();
        while (e.hasMoreElements()) {
            headers = (String) e.nextElement();
            if (headers != null) {
                out.print(headers + "<br>");
                out.print(request.getHeader(headers) + "<br>");
            }
        }
        out.flush();
        out.close();
    }
}
