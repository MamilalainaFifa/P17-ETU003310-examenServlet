package com.monprojet;

public class Depense {
    private int id;
    private String libelle;
    private double montant;
    private int creditId;

    public Depense() {}

    public Depense(int id, String libelle, double montant, int creditId) {
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
        this.creditId = creditId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public int getCreditId() { return creditId; }
    public void setCreditId(int creditId) { this.creditId = creditId; }
}