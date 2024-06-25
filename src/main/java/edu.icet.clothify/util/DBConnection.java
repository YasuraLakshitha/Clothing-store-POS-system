package edu.icet.clothify.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;

    private Connection conn;

    private DBConnection() {
        try {
            new com.mysql.cj.jdbc.Driver();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clothify_Store", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return instance = instance == null ? new DBConnection() : instance;
    }

    public Connection getConnection() {
        return conn;
    }

}
