package fr.insalyon.dasi.positif.Metier.Modele;

import fr.insalyon.dasi.positif.util.Astro;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String adressePost;
    private String numTel;
    @Column(unique = true)
    private String adresseMail;
    private String signeAstro;
    private String colBonheur;
    private String animalTotem;
    private String motDePasse;
    @OneToMany(mappedBy = "client")
    private List<Consultation> LConsult;

    public Client() {
    }

    public Client(String civilite, String nom, String prenom, Date dateNaissance, String adressePost, String numTel, String adresseMail, String motDePasse) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adressePost = adressePost;
        this.numTel = numTel;
        this.adresseMail = adresseMail;
        this.motDePasse = motDePasse;

        Astro a = new Astro();
        try {
            List<String> profil = a.getProfil(this.prenom, this.dateNaissance);
            this.signeAstro = profil.get(0);
            this.colBonheur = profil.get(2);
            this.animalTotem = profil.get(3);
        } catch (IOException ex) {
        }

    }

    public Long getId() {
        return id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdressePost() {
        return adressePost;
    }

    public void setAdressePost(String adressePost) {
        this.adressePost = adressePost;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getColBonheur() {
        return colBonheur;
    }

    public void setColBonheur(String colBonheur) {
        this.colBonheur = colBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public String getSigneAstro() {
        return signeAstro;
    }

    public void setSigneAstro(String signeAstro) {
        this.signeAstro = signeAstro;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}
