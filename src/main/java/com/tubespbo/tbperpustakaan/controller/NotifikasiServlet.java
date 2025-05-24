/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tubespbo.tbperpustakaan.controller; 

import com.tubespbo.tbperpustakaan.dao.NotifikasiDAO;
import com.tubespbo.tbperpustakaan.model.Notifikasi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/notifikasi")
public class NotifikasiServlet extends HttpServlet {
    private NotifikasiDAO notifikasiDAO;
    
    @Override
    public void init() throws ServletException {
        notifikasiDAO = new NotifikasiDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("auth");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action == null || action.equals("list")) {
            showNotifikasi(request, response, userId);
        } else if (action.equals("mark-read")) {
            markAsRead(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("mark-read")) {
            markAsRead(request, response);
        }
    }
    
    private void showNotifikasi(HttpServletRequest request, HttpServletResponse response, int userId) 
            throws ServletException, IOException {
        List<Notifikasi> notifikasiList = notifikasiDAO.getNotifikasiByPengguna(userId);
        request.setAttribute("notifikasiList", notifikasiList);
        request.getRequestDispatcher("/WEB-INF/views/notifikasi.jsp").forward(request, response);
    }
    
    private void markAsRead(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = notifikasiDAO.ubahStatusNotifikasi(id, true);
        
        if (success) {
            response.sendRedirect("notifikasi?message=marked");
        } else {
            response.sendRedirect("notifikasi?message=error");
        }
    }
}