package fr.insalyon.dasi.positif.Metier;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public abstract class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String talent;
    private String description;
    @OneToMany(mappedBy = "consultation")
    private List<Consultation> LConsult;

    public Medium() {
    }

    public Medium(String nom, String talent, String description) {
        this.nom = nom;
        this.talent = talent;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
