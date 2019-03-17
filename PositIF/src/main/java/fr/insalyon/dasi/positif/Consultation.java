package fr.insalyon.dasi.positif;

import java.util.Date;

public class Consultation {
    private Date dateConsultation;
    private String commentaire;

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
