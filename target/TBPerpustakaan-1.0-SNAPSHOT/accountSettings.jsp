<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.User" %> 
<%
    // Ambil objek User dari sesi
    User currentUser = (User) session.getAttribute("loggedInUser");

    // Cek apakah pengguna sudah login, jika tidak, redirect ke halaman login
    if (currentUser == null) {
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
    <%@ include file="../header/userHeader.jsp" %>
    
    
    <div class="container content-wrapper py-4">
        
        <%
            String message = (String) session.getAttribute("message");
            String messageType = (String) session.getAttribute("messageType"); // success / danger / info / warning
            if (message != null && messageType != null) {
        %>
            <div class="alert alert-<%= messageType %> alert-dismissible fade show mt-3" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <%
                session.removeAttribute("message");
                session.removeAttribute("messageType");
            }
        %>
        
        <h2>Pengaturan Akun</h2>

        <!-- Form Ubah Data Diri -->
        <form action="UpdateProfileServlet" method="post" class="mb-4">
            <h4>Ubah Data Diri</h4>
            <div class="mb-3">
                <label class="form-label">Nama</label>
                <input type="text" name="name" class="form-control" value="<%= currentUser.getName() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Nomor HP</label>
                <input type="text" name="phoneNumber" class="form-control" value="<%= currentUser.getPhoneNumber() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Alamat</label>
                <textarea name="address" class="form-control" required><%= currentUser.getAddress() %></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Simpan Perubahan</button>
        </form>

        <!-- Form Ubah Password -->
        <form action="ChangePasswordServlet" method="post">
            <h4>Ubah Password</h4>
            <div class="mb-3">
                <label class="form-label">Password Lama</label>
                <input type="password" name="oldPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password Baru</label>
                <input type="password" name="newPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Konfirmasi Password Baru</label>
                <input type="password" name="confirmPassword" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-warning">Ubah Password</button>
        </form>
    </div>

    <%@ include file="../footer/userFooter.jsp" %>

</div>
</body>
</html>