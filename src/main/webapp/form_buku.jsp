<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ page import="java.util.UUID" %>  
<%  
    String action = request.getParameter("action");  
    String idBuku = "";  
    String judul = "";  
    String penulis = "";  
    String penerbit = "";  
    String tahunTerbitStr = "";  
    String idStatus = "Tersedia"; // default  
    boolean isEdit = false;  

    if ("edit".equalsIgnoreCase(action)) {  
        // Kalau edit, get data buku dari parameter atau atribut  
        com.tubespbo.tbperpustakaan.model.Buku buku = (com.tubespbo.tbperpustakaan.model.Buku) request.getAttribute("buku");  
        if (buku != null) {  
            idBuku = buku.getIdBuku();  
            judul = buku.getJudul();  
            penulis = buku.getPenulis();  
            penerbit = buku.getPenerbit();  
            tahunTerbitStr = String.valueOf(buku.getTahunTerbit());  
            idStatus = buku.getIdStatus();  
            isEdit = true;  
        }  
    } else {  
        // Jika tambah baru, generate ID otomatis  
        idBuku = UUID.randomUUID().toString();  
    }  
%>  
<html>  
<head>  
    <title><%= isEdit ? "Edit Buku" : "Tambah Buku" %></title>  
    <style>  
        body { font-family: sans-serif; margin: 20px; background-color: #f9f9f9; }  
        h2 { text-align: center; margin-bottom: 20px; color: #007bff; }  
        form { max-width: 500px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 6px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }  
        .form-group { margin-bottom: 15px; }  
        label { display: block; margin-bottom: 5px; font-weight: bold; }  
        input[type=text], input[type=number], select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; }  
        .btn { width: 100%; padding: 10px; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; margin-top: 10px; }  
        .btn-submit { background-color: #28a745; color: white; }  
        .btn-cancel { background-color: #6c757d; color: white; margin-top: 10px; }  
        .btn-submit:hover { background-color: #218838; }  
        .btn-cancel:hover { background-color: #5a6268; }  
    </style>  
</head>  
<body>  
<h2><%= isEdit ? "Edit Buku" : "Tambah Buku" %></h2>  
<form method="post" action="BukuServlet">  
    <!-- Hidden input utk action -->  
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "add" %>">  
    <input type="hidden" name="idBuku" value="<%= idBuku %>">  

    <div class="form-group">  
        <label for="judul">Judul</label>  
        <input type="text" id="judul" name="judul" value="<%= judul %>" required>  
    </div>  
    <div class="form-group">  
        <label for="penulis">Penulis</label>  
        <input type="text" id="penulis" name="penulis" value="<%= penulis %>" required>  
    </div>  
    <div class="form-group">  
        <label for="penerbit">Penerbit</label>  
        <input type="text" id="penerbit" name="penerbit" value="<%= penerbit %>" required>  
    </div>  
    <div class="form-group">  
        <label for="tahunTerbit">Tahun Terbit