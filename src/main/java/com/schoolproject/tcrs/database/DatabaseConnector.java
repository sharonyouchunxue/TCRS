package com.schoolproject.tcrs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:mysql://192.168.2.13:3306/TCRS";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "0610431043";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
