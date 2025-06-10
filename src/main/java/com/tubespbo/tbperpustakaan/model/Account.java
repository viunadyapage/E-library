/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;
import java.util.Date;
import java.util.UUID;

public abstract class Account {

    protected String accountID;
    protected String email;
    protected String password;
    protected boolean isActive;
    protected Date registDate;
    protected String accountType;
    
    public Account() {
    }
    public Account(String email, String password, String accountType) {
        this.accountID = UUID.randomUUID().toString(); 
        this.email = email;
        this.password = password;
        this.isActive = true;
        this.registDate = new Date();
        this.accountType = accountType;
    }

    public Account(String accountID, String email, String hashedPassword, boolean isActive, Date registDate, String accountType) {
        this.accountID = accountID;
        this.email = email;
        this.password = hashedPassword;
        this.isActive = isActive;
        this.registDate = registDate;
        this.accountType = accountType;
    }

    public String getAccountID() {
        return accountID;
    }
    
    public void setAccountID(String accountID) {
        this.accountID = accountID; 
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public boolean isAccountActive() {
        return this.isActive;
    }
    
    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Permintaan perubahan password untuk akun: " + this.email + ". Password di objek telah diupdate (perlu di-hash dan disimpan ke DB).");
    }
    
}
