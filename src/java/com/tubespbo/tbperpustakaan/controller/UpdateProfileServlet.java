package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    User user = (User) session.getAttribute("loggedInUser");

    String name = request.getParameter("name");
    String phone = request.getParameter("phoneNumber");
    String address = request.getParameter("address");

    user.updateProfile(name, phone, address);
    AuthDAO auth = new AuthDAO();

    try {
        boolean saved = auth.updateProfile(user);

        if (saved) {
            session.setAttribute("loggedInUser", user);
            session.setAttribute("message", "Profil berhasil diperbarui!");
            session.setAttribute("messageType", "success");
        } else {
            session.setAttribute("message", "Gagal memperbarui profil.");
            session.setAttribute("messageType", "danger");
        }

        response.sendRedirect("accountSettings.jsp");
        return;

    } catch (SQLException ex) {
        Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        session.setAttribute("message", "Terjadi kesalahan saat memperbarui profil.");
        session.setAttribute("messageType", "danger");
        response.sendRedirect("accountSettings.jsp");
    }
}

}