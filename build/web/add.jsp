<%@ page import="model.Library, dao.LibraryDAO" %>
<%
if ("POST".equalsIgnoreCase(request.getMethod())) {
    Library lib = new Library(
        request.getParameter("id"),
        request.getParameter("name"),
        request.getParameter("location"),
        request.getParameter("phoneNumber"),
        request.getParameter("operationalHour")
    );
    LibraryDAO.insertLibrary(lib);
    response.sendRedirect("index.jsp");
}
%>
<h2>Tambah Perpustakaan</h2>
<form method="post">
    ID: <input name="id"><br>
    Nama: <input name="name"><br>
    Lokasi: <input name="location"><br>
    Telepon: <input name="phoneNumber"><br>
    Jam Operasional: <input name="operationalHour"><br>
    <button type="submit">Simpan</button>
</form>
