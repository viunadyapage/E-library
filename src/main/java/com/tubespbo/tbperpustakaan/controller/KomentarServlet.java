package com.tubespbo.tbperpustakaan.controller;

import dao.KomentarDAO;
import model.Komentar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/register") // URL mapping untuk servlet ini
public class KomentarServlet extends HttpServlet {
    private KomentarDAO komentarDAO;
    
    @Override
    public void init() throws ServletException {
        komentarDAO = new KomentarDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        switch (action) {
            case "add":
                addKomentar(request, response);
                break;
            case "delete":
                deleteKomentar(request, response);
                break;
            default:
                response.sendRedirect("berita");
        }
    }
    
    private void addKomentar(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login");
            return;
        }
        
        String isi = request.getParameter("isi");
        int idBerita = Integer.parseInt(request.getParameter("idBerita"));
        
        Komentar komentar = new Komentar();
        komentar.setIsi(isi);
        komentar.setTanggal(new Date());
        komentar.setIdPengguna(userId);
        komentar.setIdBerita(idBerita);
        
        boolean success = komentarDAO.tambahKomentar(komentar);
        
        if (success) {
            response.sendRedirect("berita?action=view&id=" + idBerita + "&message=comment_added");
        } else {
            response.sendRedirect("berita?action=view&id=" + idBerita + "&message=error");
        }
    }
    
    private void deleteKomentar(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idBerita = Integer.parseInt(request.getParameter("idBerita"));
        
        boolean success = komentarDAO.hapusKomentar(id);
        
        if (success) {
            response.sendRedirect("berita?action=view&id=" + idBerita + "&message=comment_deleted");
        } else {
            response.sendRedirect("berita?action=view&id=" + idBerita + "&message=error");
        }
    }
}