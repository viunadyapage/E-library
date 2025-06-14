<%-- 
    Document   : test
    Created on : Jun 13, 2025, 8:10:12 PM
    Author     : kyaku
--%>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Daftar Pengembalian Buku</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 1000px; margin: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn { 
            display: inline-block; 
            padding: 8px 12px; 
            background-color: #4CAF50; 
            color: white; 
            text-decoration: none; 
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Daftar Pengembalian Buku</h1>
        <a href="form_pengembalian.jsp" class="btn">Tambah Pengembalian</a>
        
        <table>
            <thead>
                <tr>
                    <th>ID Pengembalian</th>
                    <th>ID Peminjaman</th>
                    <th>Peminjam</th>
                    <th>Judul Buku</th>
                    <th>Tanggal Pengembalian</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev", "username", "password");
                        
                        String sql = "SELECT pg.ID_pengembalian, pg.ID_peminjaman, " +
                                      "u.nama AS peminjam, b.judul AS buku, " +
                                      "pg.tanggal_pengembalian, pg.status " +
                                      "FROM pengembalian pg " +
                                      "JOIN peminjaman p ON pg.ID_peminjaman = p.ID_peminjaman " +
                                      "JOIN users u ON p.ID_user = u.ID_user " +
                                      "JOIN buku b ON p.ID_buku = b.ID_buku " +
                                      "ORDER BY pg.tanggal_pengembalian DESC";
                        
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getInt("ID_pengembalian") + "</td>");
                            out.println("<td>" + rs.getInt("ID_peminjaman") + "</td>");
                            out.println("<td>" + rs.getString("peminjam") + "</td>");
                            out.println("<td>" + rs.getString("buku") + "</td>");
                            out.println("<td>" + rs.getDate("tanggal_pengembalian") + "</td>");
                            out.println("<td>" + rs.getString("status") + "</td>");
                            out.println("</tr>");
                        }
                    } catch (Exception e) {
                        out.println("<tr><td colspan='6'>Error: " + e.getMessage() + "</td></tr>");
                    } finally {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
