<%-- 
    Document   : bookingproses
    Created on : Jun 12, 2025, 6:46:31 PM
    Author     : kyaku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="project.DBConnection" %>

<%
    String idBuku = request.getParameter("idBuku");
    // Ambil accountID dari session (pastikan sudah login)
    String accountID = (String) session.getAttribute("accountID");
    
    if (accountID == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = null;
    try {
        conn = DBConnection.getConnection();
        
        // 1. Insert data booking
        String sql = "INSERT INTO booking (accountID, idBuku, tanggal_booking) VALUES (?, ?, NOW())";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, accountID);
        ps.setString(2, idBuku);
        int result = ps.executeUpdate();

        // 2. Update status buku menjadi 'Dipinjam'
        if (result > 0) {
            String updateSql = "UPDATE buku SET idStatus = (SELECT idStatus FROM status_buku WHERE namaStatus = 'Dipinjam') WHERE idBuku = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateSql);
            updatePs.setString(1, idBuku);
            updatePs.executeUpdate();
        }

        conn.close();
        response.sendRedirect("booking.jsp"); // Redirect setelah berhasil
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
%>
