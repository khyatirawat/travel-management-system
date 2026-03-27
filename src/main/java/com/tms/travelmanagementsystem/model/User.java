package com.tms.travelmanagementsystem.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String pwd;
    
    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPwd() {
    return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}