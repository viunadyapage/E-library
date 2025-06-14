/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.dao;

import com.tubespbo.tbperpustakaan.utils.DBConnection;
import com.tubespbo.tbperpustakaan.model.Berita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeritaDAO {
    
    public void tambahBerita(Berita berita) throws SQLException {
        String sql = "INSERT INTO berita (judul, isi, tanggal, penulis) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, berita.getJudul());
            stmt.setString(2, berita.getIsi());
            stmt.setTimestamp(3, new Timestamp(berita.getTanggal().getTime()));
            stmt.setString(4, berita.getPenulis());
            stmt.executeUpdate();
        }
    }
    
    public List<Berita> getAllBerita() throws SQLException {
        List<Berita> beritaList = new ArrayList<>();
        String sql = "SELECT * FROM berita ORDER BY tanggal DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Berita berita = new Berita();
                berita.setId(rs.getInt("id"));
                berita.setJudul(rs.getString("judul"));
                berita.setIsi(rs.getString("isi"));
                berita.setTanggal(rs.getTimestamp("tanggal"));
                berita.setPenulis(rs.getString("penulis"));
                beritaList.add(berita);
            }
        }
        return beritaList;
    }
    
    public Berita getBeritaById(int id) throws SQLException {
        String sql = "SELECT * FROM berita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Berita berita = new Berita();
                berita.setId(rs.getInt("id"));
                berita.setJudul(rs.getString("judul"));
                berita.setIsi(rs.getString("isi"));
                berita.setTanggal(rs.getTimestamp("tanggal"));
                berita.setPenulis(rs.getString("penulis"));
                return berita;
            }
        }
        return null;
    }
    
    public void updateBerita(Berita berita) throws SQLException {
        String sql = "UPDATE berita SET judul = ?, isi = ?, penulis = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, berita.getJudul());
            stmt.setString(2, berita.getIsi());
            stmt.setString(3, berita.getPenulis());
            stmt.setInt(4, berita.getId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteBerita(int id) throws SQLException {
        String sql = "DELETE FROM berita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
