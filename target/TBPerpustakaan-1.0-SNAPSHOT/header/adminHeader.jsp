<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Perpus Admin</a>
  <div class="collapse navbar-collapse">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item"><a class="nav-link" href="adminDashboard.jsp">Dashboard</a></li>
      <li class="nav-item"><a class="nav-link" href="manageUsers.jsp">Kelola Pengguna</a></li>
      <li class="nav-item"><a class="nav-link" href="report.jsp">Laporan</a></li>
    </ul>
    <span class="navbar-text mr-3">Halo, <c:out value="${sessionScope.username}" /></span>
    <a class="btn btn-outline-light" href="logout.jsp">Logout</a>
  </div>
</nav>