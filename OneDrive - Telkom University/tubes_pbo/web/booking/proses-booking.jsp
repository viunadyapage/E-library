<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.UUID" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Berhasil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #27ae60;
        }
        .btn-kembali {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-kembali:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        
        <h1>Booking Berhasil!</h1>
        <p>Buku telah berhasil dibooking.</p>
        <p>Silahkan menuju ke perpustakaan untuk mengambil buku tersebut.</p>
        
        <a href="form_booking.jsp" class="btn-kembali">Kembali ke Daftar Buku</a>
    </div>
</body>
</html>