<%@ page import="com.tubespbo.tbperpustakaan.model.Admin" %>
<%
    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    if (admin == null) {
        response.sendRedirect("loginAdmin.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header/adminHeader.jsp" %>
<div class="container mt-4">
    <h3>Selamat datang, <%= admin.getUsername() %>!</h3>
    <p>Ini adalah dashboard admin.</p>
</div>
<%@ include file="footer/userFooter.jsp" %>
</body>
</html>
