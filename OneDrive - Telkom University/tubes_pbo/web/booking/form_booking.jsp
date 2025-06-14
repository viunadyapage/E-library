<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Buku</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { 
            background-color: #4CAF50; 
            color: white; 
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">Daftar Buku Yang Dapat Di Booking</h1>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Judul</th>
            <th>Pengarang</th>
            <th>Aksi</th>
        </tr>
        <%
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev", "root", "");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM buku WHERE status = 'Tersedia'");
            
            while(rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("ID_buku") %></td>
            <td><%= rs.getString("judul") %></td>
            <td><%= rs.getString("pengarang") %></td>
            <td>
                <form action="proses-booking.jsp" method="post">
                    <input type="hidden" name="id_buku" value="<%= rs.getInt("ID_buku") %>">
                    <input type="hidden" name="judul" value="<%= rs.getString("judul") %>">
                    <input type="submit" class="btn" value="Booking">
                </form>
            </td>
        </tr>
        <%
            }
            con.close();
        } catch(Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
        %>
    </table>
</body>
</html>