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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Konfirmasi password tidak cocok.");
            request.getRequestDispatcher("accountSettings.jsp").forward(request, response);
            return;
        }

        boolean changed = user.changePassword(oldPassword, newPassword);

        if (changed) {
            AuthDAO auth = new AuthDAO();
            boolean saved;
            try {
                saved = auth.updatePassword(user);
                if(saved){
                    session.setAttribute("loggedInUser", user);
                    session.setAttribute("message", "Password berhasil diubah");
                    session.setAttribute("messageType", "success");
                }else{
                    session.setAttribute("message", "Password lama salah.");
                    session.setAttribute("messageType", "danger");
                }   
            } catch (SQLException ex) {
                Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("message", "Password lama salah.");
            session.setAttribute("messageType", "danger");
        }
        response.sendRedirect("accountSettings.jsp");
    }
}