<%@ page import="model.Library, dao.LibraryDAO" %>
<%
String id = request.getParameter("id");
Library lib = LibraryDAO.getLibraryById(id);

if ("POST".equalsIgnoreCase(request.getMethod())) {
    lib.setName(request.getParameter("name"));
    lib.setLocation(request.getParameter("location"));
    lib.setPhoneNumber(request.getParameter("phoneNumber"));
    lib.setOperationalHour(request.getParameter("operationalHour"));
    LibraryDAO.updateLibrary(lib);
    response.sendRedirect("index.jsp");
}
%>
<h2>Edit Perpustakaan</h2>
<form method="post">
    ID: <%= lib.getId() %> (tidak bisa diubah)<br>
    Nama: <input name="name" value="<%= lib.getName() %>"><br>
    Lokasi: <input name="location" value="<%= lib.getLocation() %>"><br>
    Telepon: <input name="phoneNumber" value="<%= lib.getPhoneNumber() %>"><br>
    Jam: <input name="operationalHour" value="<%= lib.getOperationalHour() %>"><br>
    <button type="submit">Update</button>
</form>
