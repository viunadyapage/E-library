/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tubespbo.tbperpustakaan.model;

/**
 *
 * @author ASUS
 */

import java.util.Date;

public class User extends Account {

    private String username;
    private String name;
    private String phoneNumber;
    private String address;

    public User(String email, String password, String username, String name, String phoneNumber, String address) {
        super(email, password, "USER");
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(String accountID, String email, String hashedPassword, boolean isActive, Date registDate,
                String username, String name, String phoneNumber, String address) {
        super(accountID, email, hashedPassword, isActive, registDate, "USER");
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void updateProfile(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (this.password.equals(oldPass)) {
            this.password = newPass;
            return true;
        }
        return false;
    }
}