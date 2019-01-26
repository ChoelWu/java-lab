package com.choel;

import java.sql.*;

public class MysqlConnect {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8";
        String user = "root";
        String pwd = "root";
        Connection conn = null;
        String sql = "insert into student(id, name, class_id, grade_id) value(?, ?, ?, ?)";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 3);
            pstmt.setString(2, "zhangFei");
            pstmt.setInt(3, 12);
            pstmt.setInt(4, 22);
            pstmt.execute();
            sql = "select * from student";
            pstmt = conn.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                System.out.println("id:" + res.getInt("id") + " | name" + res.getString("name") + " | class_id:" + res.getInt("class_id") + " | grade_id:" + res.getInt("grade_id"));
            }
            if(res != null) {
                res.close();
            }
            if(pstmt != null) {
                pstmt.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
