/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;

import java.util.Date;

public class Berita {
    private int id;
    private String judul;
    private String isi;
    private Date tanggal;
    private String penulis;
    
    // Constructor
    public Berita() {}
    
    public Berita(int id, String judul, String isi, Date tanggal, String penulis) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.tanggal = tanggal;
        this.penulis = penulis;
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
}

