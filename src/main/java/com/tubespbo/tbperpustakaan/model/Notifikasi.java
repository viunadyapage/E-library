/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;

import java.util.Date;

public class Notifikasi {
    private int id;
    private String isi;
    private Date tanggal;
    private String tipe;
    private boolean dibaca;
    private int idPengguna;
    
    // Constructor
    public Notifikasi() {}
    
    public Notifikasi(int id, String isi, Date tanggal, String tipe, boolean dibaca, int idPengguna) {
        this.id = id;
        this.isi = isi;
        this.tanggal = tanggal;
        this.tipe = tipe;
        this.dibaca = dibaca;
        this.idPengguna = idPengguna;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getIsi() { return isi; }
    public void setIsi(String isi) { this.isi = isi; }
    
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    
    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }
    
    public boolean isDibaca() { return dibaca; }
    public void setDibaca(boolean dibaca) { this.dibaca = dibaca; }
    
    public int getIdPengguna() { return idPengguna; }
    public void setIdPengguna(int idPengguna) { this.idPengguna = idPengguna; }
}