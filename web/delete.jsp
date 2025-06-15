<%@ page import="dao.LibraryDAO" %>
<%
String id = request.getParameter("id");
LibraryDAO.deleteLibrary(id);
response.sendRedirect("index.jsp");
%>
