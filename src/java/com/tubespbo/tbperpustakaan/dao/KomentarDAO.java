/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.dao;

import com.tubespbo.tbperpustakaan.utils.DBConnection;
import com.tubespbo.tbperpustakaan.model.Komentar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KomentarDAO {
    
    public void tambahKomentar(Komentar komentar) throws SQLException {
        String sql = "INSERT INTO komentar (isi, tanggal, username, id_berita) VALUES (?, ?, ?, ?)";
        
      //  System.out.println("HELLO" + sql );
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, komentar.getIsi());
            stmt.setTimestamp(2, new Timestamp(komentar.getTanggal().getTime()));
            stmt.setString(3, komentar.getIdPengguna());
            stmt.setInt(4, komentar.getIdBerita());
            stmt.executeUpdate();
        }
    }
    
    public List<Komentar> getKomentarByBerita(int idBerita) throws SQLException {
        List<Komentar> komentarList = new ArrayList<>();
        String sql = "SELECT * FROM komentar WHERE id_berita = ? ORDER BY tanggal DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idBerita);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setId(rs.getInt("id"));
                komentar.setIsi(rs.getString("isi"));
                komentar.setTanggal(rs.getTimestamp("tanggal"));
                komentar.setIdPengguna(rs.getString("username"));
                komentar.setIdBerita(rs.getInt("id_berita"));
                komentarList.add(komentar);
            }
        }
        return komentarList;
    }
    
    public void deleteKomentar(int id) throws SQLException {
        String sql = "DELETE FROM komentar WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
