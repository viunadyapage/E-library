<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.Admin" %>
<%
    Admin loggedAdmin = (Admin) session.getAttribute("loggedInAdmin");
    if (loggedAdmin == null || !"SUPER_ADMIN".equalsIgnoreCase(loggedAdmin.getRoleID())) {
        response.sendRedirect("adminDashboard.jsp");
        return;
    }

    String message = (String) session.getAttribute("message");
    String messageType = (String) session.getAttribute("messageType");
    session.removeAttribute("message");
    session.removeAttribute("messageType");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tambah Admin</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="page-wrapper d-flex flex-column min-vh-100">
    <%@ include file="../header/adminHeader.jsp" %>

    <div class="container content-wrapper py-4">
        <h3 class="mb-4">Tambah Admin Baru</h3>

        <% if (message != null) { %>
            <div class="alert alert-<%= messageType %> alert-dismissible fade show" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>

        <form action="AddAdminServlet" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input required type="text" class="form-control" id="username" name="username">
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input required type="email" class="form-control" id="email" name="email">
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input required type="password" class="form-control" id="password" name="password">
            </div>

            <div class="mb-3">
                <label for="perpusID" class="form-label">Perpus ID</label>
                <input type="text" class="form-control" id="perpusID" name="perpusID">
            </div>

            <div class="mb-3">
                <label for="roleID" class="form-label">Role</label>
                <select class="form-select" id="roleID" name="roleID" required>
                    <option value="ADMIN">ADMIN</option>
                    <option value="SUPER_ADMIN">SUPER_ADMIN</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Simpan Admin</button>
            <a href="manageAdmins.jsp" class="btn btn-secondary">Kembali</a>
        </form>
    </div>

    <%@ include file="../footer/adminFooter.jsp" %>
</div>
</body>
</html>
