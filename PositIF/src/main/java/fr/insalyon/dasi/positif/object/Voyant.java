package fr.insalyon.dasi.positif.object;


public class Voyant extends Medium{
    private String specialite;

    public Voyant() {
    }
    
    public Voyant(String specialite){
        super();
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
}
