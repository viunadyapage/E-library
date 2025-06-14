/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;
import com.tubespbo.tbperpustakaan.model.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;






@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInAdmin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        Admin loggedAdmin = (Admin) session.getAttribute("loggedInAdmin");
        if (!"SUPER_ADMIN".equalsIgnoreCase(loggedAdmin.getRoleID())) {
            session.setAttribute("message", "Anda tidak memiliki akses.");
            session.setAttribute("messageType", "danger");
            response.sendRedirect("manageAdmins.jsp");
            return;
        }

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String perpusID = request.getParameter("perpusID");
        String roleID = request.getParameter("roleID");

        Admin newAdmin = new Admin();
        newAdmin.setAccountID(UUID.randomUUID().toString());
        newAdmin.setUsername(username);
        newAdmin.setEmail(email);
        newAdmin.setPassword(password);
        newAdmin.setPerpusID(perpusID);
        newAdmin.setRoleID(roleID);
        newAdmin.setActive(true);
        newAdmin.setRegistDate(java.sql.Date.valueOf(LocalDate.now()));

        AuthDAO authDAO = new AuthDAO();
        try {
            boolean success = authDAO.registerAdmin(newAdmin);
            if (success) {
                session.setAttribute("message", "Admin berhasil ditambahkan.");
                session.setAttribute("messageType", "success");
            } else {
                session.setAttribute("message", "Gagal menambahkan admin.");
                session.setAttribute("messageType", "danger");
            }
        } catch (SQLException e) {
            session.setAttribute("message", "Terjadi kesalahan: " + e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace();
        }

        response.sendRedirect("manageAdmins.jsp");
    }
}
