<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Daftar Buku</title>
    <style>
        body { font-family: sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #007bff; text-align: center; margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        a { text-decoration: none; color: #007bff; margin: 0 5px; }
        a:hover { text-decoration: underline; }
        .btn { padding: 8px 12px; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; margin: 0 5px; }
        .btn-edit { background-color: #ffc107; color: #fff; }
        .btn-hapus { background-color: #dc3545; color: #fff; }
        .btn-tambah { background-color: #28a745; color: #fff; padding: 10px 20px; margin-bottom: 20px; display: inline-block; }
        .btn-tambah:hover { background-color: #218838; }
    </style>
</head>
<body>

<h1>Daftar Buku</h1>

<a href="view/form_buku.jsp?action=add" class="btn btn-tambah">Tambah Buku</a>

<table>
    <tr>
        <th>ID</th>
        <th>Judul</th>
        <th>Penulis</th>
        <th>Penerbit</th>
        <th>Tahun Terbit</th>
        <th>Status</th>
        <th>Aksi</th>
    </tr>
    <% 
        List<com.tubespbo.tbperpustakaan.model.Buku> listBuku = (List<com.tubespbo.tbperpustakaan.model.Buku>) request.getAttribute("listBuku");
        if (listBuku != null && !listBuku.isEmpty()) {
            for (com.tubespbo.tbperpustakaan.model.Buku b : listBuku) {
    %>
    <tr>
        <td><%= b.getIdBuku() %></td>
        <td><%= b.getJudul() %></td>
        <td><%= b.getPenulis() %></td>
        <td><%= b.getPenerbit() %></td>
        <td><%= b.getTahunTerbit() %></td>
        <td><%= b.getIdStatus() %></td>
        <td>
            <a href="BukuServlet?action=edit&id=<%= b.getIdBuku() %>" class="btn btn-edit">Edit</a>
            <a href="BukuServlet?action=delete&id=<%= b.getIdBuku() %>" class="btn btn-hapus" onclick="return confirm('Yakin hapus data ini?')">Hapus</a>
        </td>
    </tr>
    <%         }
        } else {
    %>
    <tr>
        <td colspan="7">Tidak ada data buku.</td>
    </tr>
    <% } %>
</table>

</body>
</html>