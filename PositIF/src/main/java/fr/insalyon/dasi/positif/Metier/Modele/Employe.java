
package fr.insalyon.dasi.positif.Metier.Modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Nom ;
    private String Prenom ;
    private String MotDePasse;
    private String AdresseMail;
    private Boolean Disponibilite;
    private String Type;
    @OneToMany
    private List<Medium> MediumCorres;
    @OneToMany(mappedBy = "consultation")
    private List<Consultation> LConsult;
    
   
    public Employe(){}

    public Employe(String Nom, String Prenom, List<Medium> MediumCorres, String MotDePasse, String AdresseMail, Boolean Disponibilite, String Type) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.MediumCorres = MediumCorres;
        this.MotDePasse = MotDePasse;
        this.AdresseMail = AdresseMail;
        this.Disponibilite = Disponibilite;
        this.Type = Type;
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

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    
}




