<%-- 
    Document   : newjsp
    Created on : Jun 12, 2025, 7:48:52 PM
    Author     : kyaku
--%>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Ambil data dari form
    String idPeminjaman = request.getParameter("id_peminjaman");
    String tanggalPengembalian = request.getParameter("tanggal_pengembalian");
    String status = request.getParameter("status");
    
    // Validasi input
    if (idPeminjaman == null || tanggalPengembalian == null || status == null || 
        idPeminjaman.isEmpty() || tanggalPengembalian.isEmpty() || status.isEmpty()) {
        response.sendRedirect("form_pengembalian.jsp?error=Data tidak lengkap");
        return;
    }
    
    Connection conn = null;
    PreparedStatement stmt = null;
    String message = "";
    
    try {
        // Koneksi ke database
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev", "username", "password");
        
        // Insert data pengembalian
        String sql = "INSERT INTO pengembalian (ID_peminjaman, tanggal_pengembalian, status) VALUES (?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(idPeminjaman));
        stmt.setDate(2, Date.valueOf(tanggalPengembalian));
        stmt.setString(3, status);
        
        int rowsAffected = stmt.executeUpdate();
        
        if (rowsAffected > 0) {
            message = "Pengembalian berhasil diproses!";
        } else {
            message = "Gagal memproses pengembalian.";
        }
    } catch (Exception e) {
        message = "Error: " + e.getMessage();
    } finally {
        // Tutup koneksi
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Proses Pengembalian</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 600px; margin: auto; text-align: center; }
        .success { color: green; }
        .error { color: red; }
        .btn { 
            display: inline-block; 
            padding: 10px 15px; 
            background-color: #4CAF50; 
            color: white; 
            text-decoration: none; 
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Hasil Proses Pengembalian</h1>
        <a href="form_pengembalian.jsp" class="btn">Kembali ke Form</a>
        <a href="daftar_pengembalian.jsp" class="btn">Lihat Daftar Pengembalian</a>
    </div>
</body>
</html>