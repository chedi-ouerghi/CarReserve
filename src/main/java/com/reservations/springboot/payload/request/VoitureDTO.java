
package com.reservations.springboot.payload.request;

public class VoitureDTO {

    private String modeleNom;
    private String marqueNom;
    private Integer annee;
    private Double prixParJour;
    private String categorieNom;
    private String statutDisponibilite;

    public VoitureDTO(String modeleNom, String marqueNom, Integer annee, Double prixParJour, String categorieNom,
            String statutDisponibilite) {
        this.modeleNom = modeleNom;
        this.marqueNom = marqueNom;
        this.annee = annee;
        this.prixParJour = prixParJour;
        this.categorieNom = categorieNom;
        this.statutDisponibilite = statutDisponibilite;
    }

    // Getters and Setters
    public String getModeleNom() {
        return modeleNom;
    }

    public void setModeleNom(String modeleNom) {
        this.modeleNom = modeleNom;
    }

    public String getMarqueNom() {
        return marqueNom;
    }

    public void setMarqueNom(String marqueNom) {
        this.marqueNom = marqueNom;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Double getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(Double prixParJour) {
        this.prixParJour = prixParJour;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public String getStatutDisponibilite() {
        return statutDisponibilite;
    }

    public void setStatutDisponibilite(String statutDisponibilite) {
        this.statutDisponibilite = statutDisponibilite;
    }
}
