<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.User" %> 
<%
    // Ambil objek User dari sesi
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    // Cek apakah pengguna sudah login, jika tidak, redirect ke halaman login
    if (loggedInUser == null) {
        response.sendRedirect("login_user.jsp");
        return; // Hentikan eksekusi sisa halaman JSP
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        html, body {
            height: 100%;
            margin: 0;
        }

        .page-wrapper {
            min-height: 100%;
            display: flex;
            flex-direction: column;
        }

        .content-wrapper {
            flex: 1;
        }
    </style>
</head>
<body>
<div class="page-wrapper">

    <%@ include file="header/userHeader.jsp" %>

    <div class="container content-wrapper py-4">
        <div class="welcome-message">
        <p>Anda telah berhasil login sebagai pengguna.</p>
        <p>Email Anda: <%= loggedInUser.getEmail() %></p>
        <p>Username Anda: <%= loggedInUser.getUsername() %></p>
        <%-- Tampilkan informasi lain dari objek loggedInUser jika perlu --%>
    </div>

    <p><a href="index.html">Kembali ke Beranda</a></p>

    <%-- Di sini Anda bisa menambahkan link ke fitur-fitur pengguna lainnya --%>
    <ul>
        <li>Lihat Daftar Buku (belum dibuat)</li>
        <li>Lihat Riwayat Peminjaman (belum dibuat)</li>
        <li>Update Profil (belum dibuat)</li>
    </ul>
    </div>

    <%@ include file="footer/userFooter.jsp" %>

</div>
</body>
</html>