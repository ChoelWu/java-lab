package com.choel.pooling;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

class ConnectionPool {
    private ArrayList<Connection> pool;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int poolSize = 1;
    private static ConnectionPool instance = null;

    private ConnectionPool() {
        init();
    }

    private void init() {
        pool = new ArrayList<Connection>(poolSize);
        readConfig();
        addConnection();
    }

    synchronized void release(Connection conn) {
        pool.add(conn);
    }

    synchronized void closePool() {
        for (int i = 0; i < pool.size(); i++) {
            try {
                ((Connection) pool.get(i)).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.remove(i);
        }
    }

    static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

    private void addConnection() {
        Connection conn = null;
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                conn = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void readConfig() {
        try {
            String path = System.getProperty("user.dir") + "\\dbpool.properties";
            FileInputStream is = new FileInputStream(path);
            Properties props = new Properties();
            props.load(is);
            this.driverClassName = props.getProperty("driverClassName");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.url = props.getProperty("url");
            this.poolSize = Integer.parseInt(props.getProperty("poolSize"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("读取文件出错！！！");
        }
    }
}
