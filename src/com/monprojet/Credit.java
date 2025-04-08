package com.monprojet;

import java.util.List;

public class Credit {
    private int id;
    private String libelle;
    private double montant;
    private int userId;
    private List<Depense> depenses; // Ajouté pour stocker les dépenses associées

    public Credit() {}

    public Credit(int id, String libelle, double montant, int userId) {
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public List<Depense> getDepenses() { return depenses; }
    public void setDepenses(List<Depense> depenses) { this.depenses = depenses; }
}