package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

        // Update object
        user.updateProfile(name, phone, address);
        AuthDAO auth = new AuthDAO();
        boolean saved;
        try {
            saved = auth.updateProfile(user);
            
            if (saved) {
                session.setAttribute("loggedInUser", user);
                request.setAttribute("message", "Data telah diubah");
            } else {
                request.setAttribute("error", "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("accountSettings.jsp").forward(request, response);
    }
}
