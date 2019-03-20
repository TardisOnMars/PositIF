package fr.insalyon.dasi.positif.Metier.Modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Nom;
    private String Prenom;
    private String MotDePasse;
    private String numTel;
    @Column(unique = true)
    private String AdresseMail;
    private Boolean Disponibilite;
    @ManyToMany
    private List<Medium> MediumCorres;
    @OneToMany
    private List<Consultation> LConsult;

    public Employe() {
    }

    public Employe(String Nom, String Prenom, List<Medium> MediumCorres, String MotDePasse, String numTel, String AdresseMail, Boolean Disponibilite) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.MediumCorres = MediumCorres;
        this.MotDePasse = MotDePasse;
        this.numTel = numTel;
        this.AdresseMail = AdresseMail;
        this.Disponibilite = Disponibilite;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public List<Medium> getMediumCorres() {
        return MediumCorres;
    }

    public void setMediumCorres(List<Medium> MediumCorres) {
        this.MediumCorres = MediumCorres;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresseMail() {
        return AdresseMail;
    }

    public void setAdresseMail(String AdresseMail) {
        this.AdresseMail = AdresseMail;
    }

    public Boolean getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(Boolean Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public List<Consultation> getLConsult() {
        return LConsult;
    }

    public void setLConsult(List<Consultation> LConsult) {
        this.LConsult = LConsult;
    }

}
