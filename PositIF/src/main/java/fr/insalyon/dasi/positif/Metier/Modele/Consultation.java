package fr.insalyon.dasi.positif.Metier.Modele;

import java.util.Date;
import javax.persistence.ManyToOne;

public class Consultation {
    private Date dateConsultation;
    private String commentaire;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;

    public Consultation() {
    }

    public Consultation(Date dateConsultation, String commentaire) {
        this.dateConsultation = dateConsultation;
        this.commentaire = commentaire;
    }
    
    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
}
