<%@page import="com.tubespbo.tbperpustakaan.model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User userDataHeader = (User) session.getAttribute("loggedInUser");
%>

<!-- Tambahkan CDN Bootstrap jika belum -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
  .navbar-custom {
    background-color: #f8f9fa;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  }

  .navbar-brand {
    font-weight: bold;
    font-size: 1.5rem;
    color: #2c3e50;
  }

  .navbar-nav .nav-link {
    color: #2c3e50;
    transition: color 0.3s;
  }

  .navbar-nav .nav-link:hover {
    color: #007bff;
  }

  .navbar-text {
    font-weight: 500;
    color: #343a40;
  }

  .btn-logout {
    border: none;
    background-color: #dc3545;
    color: white;
    padding: 6px 14px;
    border-radius: 8px;
    transition: background-color 0.3s;
  }

  .btn-logout:hover {
    background-color: #bb2d3b;
  }
</style>

<nav class="navbar navbar-expand-lg navbar-custom px-4">
  <a class="navbar-brand">📚 E-Library</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarContent">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item"><a class="nav-link" href="user_dashboard.jsp">🏠 Dashboard</a></li>
      <li class="nav-item"><a class="nav-link" href="">📖 Booking Buku</a></li>
      <li class="nav-item"><a class="nav-link" href="">🕓 Riwayat</a></li>
    </ul>

    <div class="d-flex align-items-center">
      <span class="navbar-text me-3">Halo, <%= userDataHeader.getName() %> 👋</span>
      <span class="navbar-text me-3">
      <a href="accountSettings.jsp" class="nav-link">Settings</a></span> 
      <a href="logout" class="btn-logout">Logout</a>
    </div>
  </div>
</nav>