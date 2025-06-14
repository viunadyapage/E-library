package com.tubespbo.tbperpustakaan.controller;

import com.tubespbo.tbperpustakaan.dao.BeritaDAO;
import com.tubespbo.tbperpustakaan.dao.KomentarDAO;
import com.tubespbo.tbperpustakaan.model.Berita;
import com.tubespbo.tbperpustakaan.model.Komentar;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/berita")
public class BeritaServlet extends HttpServlet {
    private BeritaDAO beritaDAO;
    private KomentarDAO komentarDAO;
    
    @Override
    public void init() throws ServletException {
        beritaDAO = new BeritaDAO();
        komentarDAO = new KomentarDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if (action == null || action.equals("list")) {
                listBerita(request, response);
            } else if (action.equals("detail")) {
                detailBerita(request, response);
            } else if (action.equals("add")) {
                showAddForm(request, response);
            } else if (action.equals("edit")) {
                showEditForm(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if (action.equals("add")) {
                tambahBerita(request, response);
            } else if (action.equals("update")) {
                updateBerita(request, response);
            } else if (action.equals("delete")) {
                deleteBerita(request, response);
            } else if (action.equals("comment")) {
                tambahKomentar(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private void listBerita(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<Berita> beritaList = beritaDAO.getAllBerita();
        request.setAttribute("beritaList", beritaList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewBerita.jsp");
        dispatcher.forward(request, response);
    }
    
    private void detailBerita(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Berita berita = beritaDAO.getBeritaById(id);
        List<Komentar> komentarList = komentarDAO.getKomentarByBerita(id);
        
        request.setAttribute("berita", berita);
        request.setAttribute("komentarList", komentarList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("berita-detail.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("berita-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Berita berita = beritaDAO.getBeritaById(id);
        request.setAttribute("berita", berita);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewBerita.jsp");
        dispatcher.forward(request, response);
    }
    
    private void tambahBerita(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String judul = request.getParameter("judul");
        String isi = request.getParameter("isi");
        String penulis = request.getParameter("penulis");
        
        Berita berita = new Berita();
        berita.setJudul(judul);
        berita.setIsi(isi);
        berita.setPenulis(penulis);
        berita.setTanggal(new Date());
        
        beritaDAO.tambahBerita(berita);
        response.sendRedirect("berita?action=list");
    }
    
    private void updateBerita(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String judul = request.getParameter("judul");
        String isi = request.getParameter("isi");
        String penulis = request.getParameter("penulis");
        
        Berita berita = new Berita();
        berita.setId(id);
        berita.setJudul(judul);
        berita.setIsi(isi);
        berita.setPenulis(penulis);
        
        beritaDAO.updateBerita(berita);
        response.sendRedirect("berita?action=list");
    }
    
    private void deleteBerita(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        beritaDAO.deleteBerita(id);
        response.sendRedirect("berita?action=list");
    }
    
    private void tambahKomentar(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int idBerita = Integer.parseInt(request.getParameter("idBerita"));
        String isi = request.getParameter("isi");
        String idPengguna = request.getParameter("idPengguna"); // Assuming logged in user ID
        
        Komentar komentar = new Komentar();
        komentar.setIsi(isi);
        komentar.setTanggal(new Date());
        komentar.setIdPengguna(idPengguna);
        komentar.setIdBerita(idBerita);
        
        komentarDAO.tambahKomentar(komentar);
        response.sendRedirect("berita?action=detail&id=" + idBerita);
    }
}