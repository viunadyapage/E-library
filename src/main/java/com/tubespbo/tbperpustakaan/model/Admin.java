package com.tubespbo.tbperpustakaan.model;

import java.util.Date;

public class Admin extends Account {
    private String username;
    private String perpusID;
    private String roleID;
    
    public Admin() {
        super();
    }
    
    public Admin(String accountID, String email, String password, boolean isActive, Date registDate, String accountType,
                 String username, String perpusID, String roleID) {
        super(accountID, email, password, isActive, registDate, accountType);
        this.username = username;
        this.perpusID = perpusID;
        this.roleID = roleID;
        
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPerpusID() {
        return perpusID;
    }

    public void setPerpusID(String perpusID) {
        this.perpusID = perpusID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    public boolean isSuperAdmin() {
        return "SUPER_ADMIN".equalsIgnoreCase(this.roleID);
    }
}
