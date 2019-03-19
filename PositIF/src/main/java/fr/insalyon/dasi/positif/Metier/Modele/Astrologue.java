package fr.insalyon.dasi.positif.Metier.Modele;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Astrologue extends Medium implements Serializable{
    private String formation;
    private String promotion;

    public Astrologue() {
    }

    public Astrologue(String nom, String description, String formation, String promotion) {
        super(nom, "Astrologue", description);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
}
