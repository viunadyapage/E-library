<%@ page import="java.util.*, model.Library, dao.LibraryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Library List</title></head>
<body>
<h2>Daftar Perpustakaan</h2>
<a href="add.jsp">+ Tambah Baru</a><br><br>

<table border="1">
<tr>
    <th>ID</th><th>Nama</th><th>Lokasi</th><th>Telepon</th><th>Jam</th><th>Aksi</th>
</tr>
<%
    List<Library> list = LibraryDAO.getAllLibraries();
    for (Library lib : list) {
%>
<tr>
    <td><%= lib.getId() %></td>
    <td><%= lib.getName() %></td>
    <td><%= lib.getLocation() %></td>
    <td><%= lib.getPhoneNumber() %></td>
    <td><%= lib.getOperationalHour() %></td>
    <td>
        <a href="edit.jsp?id=<%= lib.getId() %>">Edit</a>
        <a href="delete.jsp?id=<%= lib.getId() %>" onclick="return confirm('Hapus?')">Hapus</a>
    </td>
</tr>
<% } %>
</table>
</body>
</html>
