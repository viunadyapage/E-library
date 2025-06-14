<%@ page import="java.util.List" %>
<%@ page import="com.tubespbo.tbperpustakaan.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kelola Pengguna</title>
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

    <%@ include file="../header/adminHeader.jsp" %>

    <div class="container content-wrapper py-4">
        <h3 class="mb-4">Daftar Pengguna</h3>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="table-light">
                <tr>
                    <th>Username</th>
                    <th>Nama</th>
                    <th>Email</th>
                    <th>No. Telepon</th>
                    <th>Alamat</th>
                    <th>Status</th>
                    <th>Tanggal Registrasi</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<User> userList = (List<User>) request.getAttribute("userList");
                    if (userList != null && !userList.isEmpty()) {
                        for (User user : userList) {
                %>
                    <tr>
                        <td><%= user.getUsername() %></td>
                        <td><%= user.getName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPhoneNumber() %></td>
                        <td><%= user.getAddress() %></td>
                        <td><%= user.isActive() ? "Aktif" : "Tidak Aktif" %></td>
                        <td><%= user.getRegistDate() %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" class="text-center">Tidak ada data pengguna.</td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="../footer/adminFooter.jsp" %>

</div>
</body>
</html>
