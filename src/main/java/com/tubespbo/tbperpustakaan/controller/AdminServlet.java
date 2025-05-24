package com.tubespbo.tbperpustakaan.controller; // Sesuaikan dengan package Anda

import dao.BeritaDAO;
import dao.KomentarDAO;
import dao.NotifikasiDAO;
import model.Berita;
import model.Notifikasi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private BeritaDAO beritaDAO;
    private KomentarDAO komentarDAO;
    private NotifikasiDAO notifikasiDAO;
    
    @Override
    public void init() throws ServletException {
        beritaDAO = new BeritaDAO();
        komentarDAO = new KomentarDAO();
        notifikasiDAO = new NotifikasiDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!isAdminLoggedIn(request, response)) {
            return;
        }
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) pathInfo = "/dashboard";
        
        switch (pathInfo) {
            case "/dashboard":
                showDashboard(request, response);
                break;
            case "/berita":
                manageBerita(request, response);
                break;
            case "/komentar":
                manageKomentar(request, response);
                break;
            case "/notifikasi":
                manageNotifikasi(request, response);
                break;
            default:
                showDashboard(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!isAdminLoggedIn(request, response)) {
            return;
        }
        
        String action = request.getParameter("action");
        
        switch (action) {
            case "send-notification":
                sendNotification(request, response);
                break;
            default:
                response.sendRedirect("admin/dashboard");
        }
    }
    
    private boolean isAdminLoggedIn(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("adminId");
        
        if (adminId == null) {
            response.sendRedirect("../auth?action=admin-login");
            return false;
        }
        return true;
    }
    
    private void showDashboard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Berita> recentBerita = beritaDAO.getDaftarBerita();
        request.setAttribute("recentBerita", recentBerita);
        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }
    
    private void manageBerita(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Berita> beritaList = beritaDAO.getDaftarBerita();
        request.setAttribute("beritaList", beritaList);
        request.getRequestDispatcher("/WEB-INF/views/admin/berita-manage.jsp").forward(request, response);
    }
    
    private void manageKomentar(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Implementation for managing comments
        request.getRequestDispatcher("/WEB-INF/views/admin/komentar-manage.jsp").forward(request, response);
    }
    
    private void manageNotifikasi(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/notifikasi-manage.jsp").forward(request, response);
    }
    
    private void sendNotification(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String isi = request.getParameter("isi");
        String tipe = request.getParameter("tipe");
        int idPengguna = Integer.parseInt(request.getParameter("idPengguna"));
        
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setIsi(isi);
        notifikasi.setTanggal(new Date());
        notifikasi.setTipe(tipe);
        notifikasi.setDibaca(false);
        notifikasi.setIdPengguna(idPengguna);
        
        boolean success = notifikasiDAO.kirimNotifikasi(notifikasi);
        
        if (success) {
            response.sendRedirect("notifikasi?message=sent");
        } else {
            response.sendRedirect("notifikasi?message=error");
        }
    }
}