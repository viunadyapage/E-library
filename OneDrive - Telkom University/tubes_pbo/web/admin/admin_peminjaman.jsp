<%-- 
    Document   : test
    Created on : Jun 13, 2025, 8:10:12 PM
    Author     : kyaku
--%>

<%@ page import="java.sql.*, project.DBConnection" %>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Proses Peminjaman Buku</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: 0 auto; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 200px; }
        input[type="text"], input[type="date"] { padding: 8px; width: 300px; }
        button { padding: 10px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Proses Peminjaman Buku</h1>
        
        <%
            String idBooking = request.getParameter("id_booking");
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
                conn = DBConnection.getConnection();
                
                // If form is submitted
                if (request.getMethod().equalsIgnoreCase("POST")) {
                    String idPeminjaman = request.getParameter("id_peminjaman");
                    String idUser = request.getParameter("id_user");
                    String idBuku = request.getParameter("id_buku");
                    String tanggalPinjam = request.getParameter("tanggal_peminjaman");
                    String batasKembali = request.getParameter("batas_pengembalian");
                    
                    // Insert into peminjaman table
                    String sql = "INSERT INTO peminjaman (ID_booking, ID_user, ID_buku, tanggal_peminjaman, batas_pengembalian, status) " +
                                 "VALUES (?, ?, ?, ?, ?, 'Dipinjam')";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, idBooking);
                    pstmt.setString(2, idUser);
                    pstmt.setString(3, idBuku);
                    pstmt.setString(4, tanggalPinjam);
                    pstmt.setString(5, batasKembali);
                    
                    int rowsAffected = pstmt.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        out.println("<div style='color: green; margin-bottom: 20px;'>Peminjaman berhasil diproses!</div>");
                        // Clear the form
                        idBooking = null;
                    }
                }
                
                // Display booking data if ID is provided
                if (idBooking != null && !idBooking.isEmpty()) {
                    String sql = "SELECT b.ID_booking, b.ID_user, u.nama AS nama_user, " +
                                "b.ID_buku, bk.judul AS judul_buku, b.tanggal_booking " +
                                "FROM booking b " +
                                "JOIN users u ON b.ID_user = u.ID_user " +
                                "JOIN buku bk ON b.ID_buku = bk.ID_buku " +
                                "WHERE b.ID_booking = ? AND b.status = 'Menunggu'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, idBooking);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
        %>
                        <h2>Data Peminjaman</h2>
                        <table>
                            <tr>
                                <th>ID Booking</th>
                                <td><%= rs.getString("ID_booking") %></td>
                            </tr>
                            <tr>
                                <th>ID User</th>
                                <td><%= rs.getString("ID_user") %></td>
                            </tr>
                            <tr>
                                <th>Nama User</th>
                                <td><%= rs.getString("nama_user") %></td>
                            </tr>
                            <tr>
                                <th>ID Buku</th>
                                <td><%= rs.getString("ID_buku") %></td>
                            </tr>
                            <tr>
                                <th>Judul Buku</th>
                                <td><%= rs.getString("judul_buku") %></td>
                            </tr>
                            <tr>
                                <th>Tanggal Booking</th>
                                <td><%= rs.getString("tanggal_booking") %></td>
                            </tr>
                        </table>
                        
                        <h2>Form Peminjaman</h2>
                        <form method="POST" action="admin_peminjaman.jsp">
                            <input type="hidden" name="id_booking" value="<%= rs.getString("ID_booking") %>">
                            <input type="hidden" name="id_user" value="<%= rs.getString("ID_user") %>">
                            <input type="hidden" name="id_buku" value="<%= rs.getString("ID_buku") %>">
                            
                            <div class="form-group">
                                <label for="tanggal_peminjaman">Tanggal Peminjaman:</label>
                                <input type="date" name="tanggal_peminjaman" id="tanggal_peminjaman" 
                                       value="<%= new java.sql.Date(System.currentTimeMillis()) %>" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="batas_pengembalian">Batas Pengembalian:</label>
                                <%
                                    // Calculate return date (7 days from now)
                                    java.util.Date today = new java.util.Date();
                                    java.util.Calendar cal = java.util.Calendar.getInstance();
                                    cal.setTime(today);
                                    cal.add(java.util.Calendar.DATE, 7);
                                    java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
                                %>
                                <input type="date" name="batas_pengembalian" id="batas_pengembalian" 
                                       value="<%= returnDate %>" required>
                            </div>
                            
                            <button type="submit">Proses Peminjaman</button>
                        </form>
        <%
                    } else {
                        out.println("<div style='color: red;'>Data booking tidak ditemukan atau sudah diproses.</div>");
                    }
                } else {
        %>
                    <h2>Cari Data Booking</h2>
                    <form method="GET" action="admin_peminjaman.jsp">
                        <div class="form-group">
                            <label for="id_booking">ID Booking:</label>
                            <input type="text" name="id_booking" id="id_booking" required>
                        </div>
                        <button type="submit">Cari</button>
                    </form>
        <%
                }
            } catch (Exception e) {
                out.println("<div style='color: red;'>Error: " + e.getMessage() + "</div>");
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            }
        %>
    </div>
</body>
</html>