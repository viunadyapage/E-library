package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.AuthDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/ToggleAdminStatusServlet")
public class ToogleAdminStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountID = request.getParameter("accountID");
        String action = request.getParameter("action");

        if (accountID == null || action == null) {
            response.sendRedirect("manageAdmins.jsp");
            return;
        }

        AuthDAO adminDAO = new AuthDAO();
        boolean success = false;

        if ("activate".equalsIgnoreCase(action)) {
            success = adminDAO.setAdminStatus(accountID, true);
        } else if ("deactivate".equalsIgnoreCase(action)) {
            success = adminDAO.setAdminStatus(accountID, false);
        }

        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "Status admin berhasil diperbarui.");
            session.setAttribute("messageType", "success");
        } else {
            session.setAttribute("message", "Gagal memperbarui status admin.");
            session.setAttribute("messageType", "danger");
        }

        response.sendRedirect("manageAdmins.jsp");
    }
}