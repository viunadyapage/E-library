<%@ page import="java.util.List" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.Admin" %>
<%@ page import="com.tubespbo.tbperpustakaan.dao.AuthDAO" %>
<%
    Admin loggedAdmin = (Admin) session.getAttribute("loggedInAdmin");
    if (loggedAdmin == null || !"SUPER_ADMIN".equalsIgnoreCase(loggedAdmin.getRoleID())) {
        response.sendRedirect("adminDashboard.jsp");
        return;
    }

    AuthDAO dao = new AuthDAO();
    List<Admin> adminList = dao.getAllAdmins();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kelola Admin</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="page-wrapper d-flex flex-column min-vh-100">
    <%@ include file="../header/adminHeader.jsp" %>

    <div class="container content-wrapper py-4">
        <%
            String message = (String) session.getAttribute("message");
            String messageType = (String) session.getAttribute("messageType");
            if (message != null && messageType != null) {
        %>
            <div class="alert alert-<%= messageType %> alert-dismissible fade show" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <%
                session.removeAttribute("message");
                session.removeAttribute("messageType");
            }
        %>
        
        <h3 class="mb-4">Kelola Admin</h3>

        <div class="mb-3">
            <a href="formAddAdmin.jsp" class="btn btn-primary">+ Tambah Admin Baru</a>
        </div>

        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Perpus ID</th>
                    <th>Status</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
            <% for (Admin a : adminList) { %>
                <tr>
                    <td><%= a.getUsername() %></td>
                    <td><%= a.getEmail() %></td>
                    <td><%= a.getPerpusID() %></td>
                    <td>
                        <span class="badge <%= a.isActive() ? "bg-success" : "bg-danger" %>">
                            <%= a.isActive() ? "Aktif" : "Nonaktif" %>
                        </span>
                    </td>
                    <td>
                        <form method="post" action="ToggleAdminStatusServlet" class="d-inline">
                            <input type="hidden" name="accountID" value="<%= a.getAccountID() %>" />
                            <input type="hidden" name="action" value="<%= a.isActive() ? "deactivate" : "activate" %>" />
                            <button class="btn btn-sm <%= a.isActive() ? "btn-danger" : "btn-success" %>">
                                <%= a.isActive() ? "Nonaktifkan" : "Aktifkan" %>
                            </button>
                        </form>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <%@ include file="../footer/adminFooter.jsp" %>
</div>
</body>
</html>
