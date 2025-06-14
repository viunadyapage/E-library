/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;


import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Berita {
    private int id;
    private String judul;
    private String isi;
    private Date tanggal;
    private String penulis;
    private List<Komentar> komentarList;
    
    public Berita() {
        this.komentarList = new ArrayList<>();
    }
    
    public Berita(int id, String judul, String isi, Date tanggal, String penulis) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.tanggal = tanggal;
        this.penulis = penulis;
        this.komentarList = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    
    public String getIsi() { return isi; }
    public void setIsi(String isi) { this.isi = isi; }
    
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    
    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    
    public List<Komentar> getKomentarList() { return komentarList; }
    public void setKomentarList(List<Komentar> komentarList) { this.komentarList = komentarList; }
    
    // Methods
    public void tambahBerita() {
        // Implementation for adding news
    }
    
    public void editBerita() {
        // Implementation for editing news
    }
    
    public void hapusBerita() {
        // Implementation for deleting news
    }
    
    public List<Berita> getDaftarBerita() {
        // Implementation for getting news list
        return new ArrayList<>();
    }
}
