package com.tubespbo.tbperpustakaan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/perpusdb"; // Ubah sesuai DB Anda
    private static final String USER = "root"; // Ubah sesuai user DB
    private static final String PASS = ""; // Ubah sesuai password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}