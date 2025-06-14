/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;

import java.util.Date;

public class Komentar {
    private int id;
    private String isi;
    private Date tanggal;
    private String idPengguna;
    private int idBerita;
    
    public Komentar() {}
    
    public Komentar(int id, String isi, Date tanggal, String idPengguna, int idBerita) {
        this.id = id;
        this.isi = isi;
        this.tanggal = tanggal;
        this.idPengguna = idPengguna;
        this.idBerita = idBerita;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getIsi() { return isi; }
    public void setIsi(String isi) { this.isi = isi; }
    
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    
    public String getIdPengguna() { return idPengguna; }
    public void setIdPengguna(String idPengguna) { this.idPengguna = idPengguna; }
    
    public int getIdBerita() { return idBerita; }
    public void setIdBerita(int idBerita) { this.idBerita = idBerita; }
    
    // Methods
    public void tambahKomentar() {
        // Implementation for adding comment
    }
    
    public void hapusKomentar() {
        // Implementation for deleting comment
    }
    
    public Komentar getKomentarByBerita() {
        // Implementation for getting comments by news
        return new Komentar();
    }
}

