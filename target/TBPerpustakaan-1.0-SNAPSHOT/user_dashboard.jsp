<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.User" %> <%-- Sesuaikan package --%>
<%
    // Ambil objek User dari sesi
    User loggedInUser = (User) session.getAttribute("loggedInUser");

    // Cek apakah pengguna sudah login, jika tidak, redirect ke halaman login
    if (loggedInUser == null) {
        response.sendRedirect("login_user.jsp");
        return; // Hentikan eksekusi sisa halaman JSP
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Pengguna</title>
    <style>
        body { font-family: sans-serif; margin: 20px; }
        .header { background-color: #007bff; color: white; padding: 15px; border-radius: 5px; margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center; }
        .header h1 { margin: 0; }
        .logout-btn { background-color: #dc3545; color: white; padding: 8px 15px; text-decoration: none; border-radius: 4px; font-size: 14px; }
        .logout-btn:hover { background-color: #c82333; }
        .welcome-message { font-size: 18px; }
    </style>
</head>
<body>
    <div class="header">
        <h1>Selamat Datang, <%= loggedInUser.getName() != null ? loggedInUser.getName() : loggedInUser.getUsername() %>!</h1>
        <a href="logout" class="logout-btn">Logout</a> <%-- Asumsi Anda akan membuat LogoutServlet --%>
    </div>

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

</body>
</html>