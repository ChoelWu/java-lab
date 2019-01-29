package com.choel.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionPoolTest {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT `id`, `name`, `phone` FROM `guestbook`;";
        long start = System.currentTimeMillis();
        ConnectionPool pool = null;
        for (int i = 0; i < 1000; i++) {
            pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {

            }
            res.close();
            stmt.close();
            pool.release(conn);
        }
        pool.closePool();
        System.out.println("经过1000次循环调用，使用连接池花费的时间：" + (System.currentTimeMillis() - start) + "ms\n");
        String hostname = "192.168.1.20";
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java_lab?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        start = System.currentTimeMillis();
        Class.forName(driverClass);
        for (int i = 0; i < 1000; i++) {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {

            }
            res.close();
            stmt.close();
            conn.close();
        }
        System.out.println("经过1000次循环调用，不使用连接池花费的时间：" + (System.currentTimeMillis() - start) + "ms");
    }
}
