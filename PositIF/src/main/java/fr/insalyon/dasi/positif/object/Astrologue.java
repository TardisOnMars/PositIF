package fr.insalyon.dasi.positif.object;

public class Astrologue extends Medium{
    private String formation;
    private String promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, String promotion) {
        super();
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
