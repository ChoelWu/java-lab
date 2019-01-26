package com.choel.guestbook;

import com.choel.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java_leb?useUnicode=true&amp;characterEncoding=UTF-8";
        String user = "root";
        String pwd = "root";
        Connection conn = null;
        String sql = "insert into guestbook (id, name, email, phone, title, content, time) value(gb_seq.nextval, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head><title>guestbook input page</title></head>");
        out.print("<body>");
        if(StringUtil.validateNull(name)) {
            out.print("对不起，姓名不能为空，请重新输入！<br>");
            out.print("<a href='"+request.getContextPath()+"/add_message.html'>添加的留言</a>");
        } else if(StringUtil.validateNull(title)) {
            out.print("对不起，主题不能为空，请您重新输入！<br>");
            out.print("<a href='"+request.getContextPath()+"/add_message.html'>添加的留言</a>");
        } else {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, pwd);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, StringUtil.filterHtml(name));
                pstmt.setString(2, StringUtil.filterHtml(request.getParameter("email")));
                pstmt.setString(3, StringUtil.filterHtml(request.getParameter("phone")));
                pstmt.setString(4, StringUtil.filterHtml(title));
                pstmt.setString(5, request.getParameter("content"));
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                pstmt.setString(6, sdf.format(new java.util.Date()));
                result = pstmt.executeUpdate();
                pstmt.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(result == 0) {
                out.println("对不起，添加留言不成功，请重新输入<br>");
                out.print("<a href='"+request.getContextPath()+"/servlet/getMessage'>查看所有留言内容</a>");
            } else {
                out.println("祝贺您，成功添加留言<br>");
                out.print("<a href='"+request.getContextPath()+"/servlet/getMessage'>查看所有留言内容</a>");
            }
            out.print("</body>");
            out.print("</html>");
            out.flush();
            out.close();
        }
    }
}
