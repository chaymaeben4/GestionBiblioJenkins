package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "Chben97531@@";
    private static  Connection dbConnection ;

    private  DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            if(dbConnection != null && !dbConnection.isClosed()) {
                return dbConnection;
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return dbConnection;
        } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Database connection error", e);
        }

    }
}
