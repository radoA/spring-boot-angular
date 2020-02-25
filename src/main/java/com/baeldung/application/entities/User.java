package com.baeldung.application.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String email;
    
    public User() {
        this.name = "";
        this.email = "";
    }
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
    	this.name=name;
    }
    
    public void setEmail(String mail) {
    	email=mail;
    }
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }
}
