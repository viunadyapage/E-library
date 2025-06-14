<%-- 
    Document   : ad_pengembalian
    Created on : Jun 12, 2025, 7:47:58 PM
    Author     : kyaku
--%>

<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Pengembalian Buku</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 600px; margin: auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        select, input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Form Pengembalian Buku</h1>
        
        <form action="proses_pengembalian.jsp" method="post">
            <div class="form-group">
                <label for="id_peminjaman">ID Peminjaman:</label>
                <input type="text" id="id_peminjaman" name="id_peminjaman" required>
            </div>
            
            <div class="form-group">
                <label for="tanggal_pengembalian">Tanggal Pengembalian:</label>
                <input type="date" id="tanggal_pengembalian" name="tanggal_pengembalian" required>
            </div>
            
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status" required>
                    <option value="">-- Pilih Status --</option>
                    <option value="Dikembalikan">Dikembalikan</option>
                    <option value="Hilang">Hilang</option>
                </select>
            </div>
            
            <button type="submit">Proses Pengembalian</button>
        </form>
    </div>
    
    <script>
        // Set tanggal hari ini sebagai default
        document.getElementById('tanggal_pengembalian').valueAsDate = new Date();
    </script>
</body>
</html>