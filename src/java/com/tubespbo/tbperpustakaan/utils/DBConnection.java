/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/perpusdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "my_root_secret"; 
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver tidak ditemukan. Pastikan sudah ditambahkan ke classpath.");
            e.printStackTrace();
            throw new RuntimeException("Gagal memuat MySQL JDBC Driver", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Koneksi ke database 'perpusdb' berhasil!");
            } else {
                System.err.println("Gagal membuat koneksi ke database.");
            }
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}