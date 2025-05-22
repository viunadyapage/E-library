/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */

CREATE DATABASE IF NOT EXISTS perpusdb
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE perpusdb;

CREATE TABLE accounts (
    accountID VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, 
    isActive BOOLEAN DEFAULT TRUE,
    registDate DATE NOT NULL,
    accountType VARCHAR(10) NOT NULL 
) ENGINE=InnoDB;

CREATE TABLE users (
    accountID VARCHAR(36) PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL, 
    name VARCHAR(255),    
    phoneNumber VARCHAR(20),                 
    address TEXT,                          
    FOREIGN KEY (accountID) REFERENCES accounts(accountID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE admins (
    accountID VARCHAR(36) PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL, 
    perpusID VARCHAR(50),                    
    roleID VARCHAR(50),                      
    FOREIGN KEY (accountID) REFERENCES accounts(accountID) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

