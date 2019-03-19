package fr.insalyon.dasi.positif.Metier.Modele;

import javax.persistence.Entity;

@Entity
public class Voyant extends Medium{
    private String specialite;

    public Voyant() {
    }
    
    public Voyant(String nom, String description, String specialite){
        super(nom, "Voyant", description);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
}
