/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.dao;

import com.tubespbo.tbperpustakaan.model.Account;
import com.tubespbo.tbperpustakaan.model.User;
import com.tubespbo.tbperpustakaan.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    private String hashPassword(String plainPassword) {
        return plainPassword;
    }

    public Account validateLogin(String email, String plainPassword) {
        Account account = null;
        String sql = "SELECT accountID, email, password, isActive, accountType FROM accounts WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    boolean isActive = rs.getBoolean("isActive");
                    String accountType = rs.getString("accountType");
                    if (isActive && hashPassword(plainPassword).equals(storedHashedPassword)) {                     
                        account = new Account(rs.getString("accountID"), rs.getString("email"), rs.getString("accountType")) {
                            
                        };
                    } else if (!isActive) {
                        System.out.println("Login gagal: Akun " + email + " tidak aktif.");
                    } else {
                        System.out.println("Login gagal: Password salah untuk " + email);
                    }
                } else {
                    System.out.println("Login gagal: Email " + email + " tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error, mungkin log atau throw custom exception
        }
        return account; // Mengembalikan objek Account jika berhasil, null jika gagal
    }

    public User validateUserLogin(String email, String plainPassword) {
        User user = null;   
        String sql = "SELECT acc.accountID, acc.email, acc.password, acc.isActive, acc.accountType,acc.registDate, " +
                     "usr.username, usr.name, usr.phoneNumber, usr.address " +
                     "FROM accounts acc LEFT JOIN users usr ON acc.accountID = usr.accountID " +
                     "WHERE acc.email = ? AND acc.accountType = 'USER'";
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Attempting login for email: " + email);
        System.out.println("Plain password received by DAO: " + plainPassword); // Hati-hati menampilkan plain password di log produksi

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println(rs);
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    boolean isActive = rs.getBoolean("isActive");

                    if (isActive && hashPassword(plainPassword).equals(storedHashedPassword)) {
                        user = new User(
                            rs.getString("accountID"),
                            rs.getString("email"),
                            rs.getString("password"), 
                            rs.getBoolean("isActive"),
                            rs.getDate("registDate"),
                            rs.getString("username"),
                            rs.getString("name"),
                            rs.getString("phoneNumber"),
                            rs.getString("address")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
