package com.monprojet;

public class User {
    private int id;
    private String nom;
    private String password;

    public User() {}
    
    public User(int id, String nom, String password) {
        this.id = id;
        this.nom = nom;
        this.password = password;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPassword(String password) { this.password = password; }
    public String getPassword(){ return password; }
}