package com.reservations.springboot.payload.request;

public class VoitureRequest {
    private String modele;
    private String categorie;
    private int annee;
    private double prixParJour;
    private String statutDisponibilite;

    // Getters and Setters
    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(double prixParJour) {
        this.prixParJour = prixParJour;
    }

    public String getStatutDisponibilite() {
        return statutDisponibilite;
    }

    public void setStatutDisponibilite(String statutDisponibilite) {
        this.statutDisponibilite = statutDisponibilite;
    }
}
