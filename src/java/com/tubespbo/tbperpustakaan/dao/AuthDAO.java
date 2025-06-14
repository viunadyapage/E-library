/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.dao;

import com.tubespbo.tbperpustakaan.model.Account;
import com.tubespbo.tbperpustakaan.model.Admin;
import com.tubespbo.tbperpustakaan.model.Berita;
import com.tubespbo.tbperpustakaan.model.User;
import com.tubespbo.tbperpustakaan.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        }
        return account; 
    }

    public User validateUserLogin(String email, String plainPassword) {
        User user = null;   
        String sql = "SELECT acc.accountID, acc.email, acc.password, acc.isActive, acc.accountType,acc.registDate, " +
                     "usr.username, usr.name, usr.phoneNumber, usr.address " +
                     "FROM accounts acc LEFT JOIN users usr ON acc.accountID = usr.accountID " +
                     "WHERE acc.email = ? AND acc.accountType = 'USER'";
        
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
    
    private String generateNextAccountID(Connection conn, String prefix) throws SQLException {
        String query = "SELECT MAX(CAST(SUBSTRING_INDEX(accountID, '_', -1) AS UNSIGNED)) AS max_id FROM accounts WHERE accountID LIKE ?";
        long nextIdNum = 1;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, prefix + "_%");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                long maxNumFromDb = rs.getLong("max_id");
                if (!rs.wasNull()) { 
                    nextIdNum = maxNumFromDb + 1;
                }
            }
        }
        return prefix + "_" + nextIdNum;
    }
    public boolean registerUser(User user) {
        String insertAccountSQL = "INSERT INTO accounts (accountID, email, password, isActive, registDate, accountType) VALUES (?, ?, ?, ?, ?, ?)";
        String insertUserSQL = "INSERT INTO users (accountID, username, name, phoneNumber, address) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String newAccountID = generateNextAccountID(conn, "user");
            Date registrationTime = new Date();

            user.setAccountID(newAccountID);
            user.setRegistDate(registrationTime);
            
            try (PreparedStatement psAccount = conn.prepareStatement(insertAccountSQL)) {
                psAccount.setString(1, user.getAccountID());
                psAccount.setString(2, user.getEmail());
                
                String hashedPassword = hashPassword(user.getPassword());
                psAccount.setString(3, hashedPassword);
                
                psAccount.setBoolean(4, user.isActive());
                psAccount.setTimestamp(5, new java.sql.Timestamp(user.getRegistDate().getTime()));
                psAccount.setString(6, user.getAccountType());
                
                psAccount.executeUpdate();
            }

            try (PreparedStatement psUser = conn.prepareStatement(insertUserSQL)) {
                psUser.setString(1, user.getAccountID());
                psUser.setString(2, user.getUsername());
                psUser.setString(3, user.getName());
                psUser.setString(4, user.getPhoneNumber());
                psUser.setString(5, user.getAddress());
                
                psUser.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("SQL Exception saat registrasi user: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.println("Transaksi di-rollback.");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public boolean updateProfile(User user) throws SQLException{
        String sql = "UPDATE users SET name=?, phoneNumber=?, address=? WHERE accountID=?";
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getAccountID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePassword(User user) throws SQLException {
        String sql = "UPDATE accounts SET password=? WHERE accountID=?";
        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getAccountID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Admin loginAdmin(String email, String password) throws SQLException {
        String sql = "SELECT a.accountID, a.email, a.password, a.isActive, a.registDate, " +
                     "adm.username, adm.perpusID, adm.roleID " +
                     "FROM accounts a JOIN admins adm ON a.accountID = adm.accountID " +
                     "WHERE a.email = ? AND a.password = ? AND a.accountType = 'ADMIN' AND a.isActive = TRUE";

        System.out.println( sql );
        System.out.println( "email dan password " + email + password  );
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAccountID(rs.getString("accountID"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setRegistDate(rs.getDate("registDate"));
                admin.setActive(rs.getBoolean("isActive"));
                admin.setUsername(rs.getString("username"));
                admin.setPerpusID(rs.getString("perpusID"));
                admin.setRoleID(rs.getString("roleID"));
                return admin;
            }
        }
        return null;
    }
    
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT a.accountID, a.email, a.isActive, a.registDate, u.username, u.name, u.phoneNumber, u.address " +
                     "FROM accounts a JOIN users u ON a.accountID = u.accountID";

        Connection conn = DBConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setAccountID(rs.getString("accountID"));
                user.setEmail(rs.getString("email"));
                user.setActive(rs.getBoolean("isActive"));
                user.setRegistDate(rs.getDate("registDate"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));

                userList.add(user);
            }
        }
        return userList;
    }
    
    
    public List<Berita> getAllBerita() throws SQLException {
        List<Berita> beritaList = new ArrayList<>();
        String sql = "SELECT * FROM berita ORDER BY tanggal DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Berita berita = new Berita();
                berita.setId(rs.getInt("id"));
                berita.setJudul(rs.getString("judul"));
                berita.setIsi(rs.getString("isi"));
                berita.setTanggal(rs.getTimestamp("tanggal"));
                berita.setPenulis(rs.getString("penulis"));
                beritaList.add(berita);
            }
        }
        return beritaList;
    }
    
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT a.accountID, a.email, a.isActive, a.accountType, u.username, u.perpusID, u.roleID " +
                     "FROM accounts a JOIN admins u ON a.accountID = u.accountID WHERE a.accountType = 'ADMIN'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAccountID(rs.getString("accountID"));
                admin.setEmail(rs.getString("email"));
                admin.setActive(rs.getBoolean("isActive"));
                admin.setAccountType(rs.getString("accountType"));
                admin.setUsername(rs.getString("username"));
                admin.setPerpusID(rs.getString("perpusID"));
                admin.setRoleID(rs.getString("roleID"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
    
    public boolean setAdminStatus(String accountID, boolean isActive) {
        String sql = "UPDATE accounts SET isActive = ? WHERE accountID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, isActive);
            stmt.setString(2, accountID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean registerAdmin(Admin admin) throws SQLException {
        String sqlAccount = "INSERT INTO accounts (accountID, email, password, isActive, registDate, accountType) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlAdmin = "INSERT INTO admins (accountID, username, perpusID, roleID) VALUES (?, ?, ?, ?)";

        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(sqlAccount);
                 PreparedStatement stmt2 = conn.prepareStatement(sqlAdmin)) {

                stmt1.setString(1, admin.getAccountID());
                stmt1.setString(2, admin.getEmail());
                stmt1.setString(3, admin.getPassword());
                stmt1.setBoolean(4, admin.isActive());
                stmt1.setDate(5, new java.sql.Date(admin.getRegistDate().getTime()));
                stmt1.setString(6, "admin");

                stmt2.setString(1, admin.getAccountID());
                stmt2.setString(2, admin.getUsername());
                stmt2.setString(3, admin.getPerpusID());
                stmt2.setString(4, admin.getRoleID());

                stmt1.executeUpdate();
                stmt2.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
