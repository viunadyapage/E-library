/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.dao;

import dao.PenggunaDAO;
import dao.AdminDAO;
import model.Pengguna;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthDao extends HttpServlet {
    private PenggunaDAO penggunaDAO;
    private AdminDAO adminDAO;
    
    @Override
    public void init() throws ServletException {
        penggunaDAO = new PenggunaDAO();
        adminDAO = new AdminDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null || action.equals("login")) {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else if (action.equals("register")) {
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        } else if (action.equals("admin-login")) {
            request.getRequestDispatcher("/WEB-INF/views/admin-login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "login":
                loginUser(request, response);
                break;
            case "register":
                registerUser(request, response);
                break;
            case "admin-login":
                loginAdmin(request, response);
                break;
            default:
                response.sendRedirect("auth");
        }
    }
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (penggunaDAO.login(email, password)) {
            Pengguna pengguna = penggunaDAO.getPenggunaByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("userId", pengguna.getId());
            session.setAttribute("userName", pengguna.getNama());
            session.setAttribute("userType", "user");
            
            response.sendRedirect("berita");
        } else {
            response.sendRedirect("auth?action=login&error=invalid");
        }
    }
    
    private void registerUser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String nama = request.getParameter("nama");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Pengguna pengguna = new Pengguna();
        pengguna.setNama(nama);
        pengguna.setEmail(email);
        pengguna.setPassword(password);
        
        if (penggunaDAO.registerPengguna(pengguna)) {
            response.sendRedirect("auth?action=login&message=registered");
        } else {
            response.sendRedirect("auth?action=register&error=failed");
        }
    }
    
    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Admin admin = adminDAO.authenticateAdmin(username, password);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("adminName", admin.getUsername());
            session.setAttribute("userType", "admin");
            
            response.sendRedirect("admin/dashboard");
        } else {
            response.sendRedirect("auth?action=admin-login&error=invalid");
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("berita");
    }
}