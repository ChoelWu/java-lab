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

@WebServlet(name = "GetMessageServlet")
public class GetMessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java_lab?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String pwd = "Wuc14561013";
        Connection conn = null;
        String sql = "select * from guestbook order by id desc";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>display message</title></head>");
        out.print("<body>");
        out.print("<a href='" + request.getContextPath() + "/add_message.html'>添加新的留言内容</a><br>");
        out.print("留言内容<br><br>");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                printRow(out, res);
            }
            res.close();
            pstmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.print("<body>");
        out.print("<html>");
        out.flush();
        out.close();
    }

    private void printRow(PrintWriter out, ResultSet res) throws SQLException {
        out.print("<table width='600' border='1' style='table-layout: fixed; word-break:break-all;'>");
        out.print("<tr><td width='50'>编号</td><td width='550'>" + res.getInt("id") + "</td></tr>");
        out.print("<tr><td>姓名</td><td>" + res.getString("name") + "</td></tr>");
        out.print("<tr><td>电话</td><td>" + StringUtil.changeNull(res.getString("phone"), "没填") + "</td></tr>");
        out.print("<tr><td>email</td><td>" + StringUtil.changeNull(res.getString("email"), "没填") + "</td></tr>");
        out.print("<tr><td valign='top'>主题</td><td>" + res.getString("title") + "</td></tr>");
        out.print("<tr><td valign='top'>内容</td><td>" + StringUtil.changeNull(res.getString("content"), "没填") + "</td></tr>");
        out.print("<tr><td>时间</td><td>" + res.getString("time") + "</td></tr>");
        out.print("</table><br>");
    }
}
