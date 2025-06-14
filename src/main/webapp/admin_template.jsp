<%@ page import="com.tubespbo.tbperpustakaan.model.Admin" %>
<%
    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    if (admin == null) {
        response.sendRedirect("loginAdmin.jsp");
        return;
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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

    <%@ include file="header/adminHeader.jsp" %>

    <div class="container content-wrapper py-4">
        
    </div>

    <%@ include file="footer/adminFooter.jsp" %>

</div>
</body>
</html>
