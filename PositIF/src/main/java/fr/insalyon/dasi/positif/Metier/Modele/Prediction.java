package fr.insalyon.dasi.positif.Metier.Modele;

import fr.insalyon.dasi.positif.util.Astro;
import fr.insalyon.dasi.positif.util.DebugLogger;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prediction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String amour;
    private String sante;
    private String travail;
    @ManyToOne
    private Consultation consultation;

    public Prediction() {
    }

    public Prediction(Consultation consultation) {
        this.consultation = consultation;
        Astro a = new Astro();
        try {
            List<String> result = a.getPredictions(consultation.getClient().getColBonheur(), consultation.getClient().getAnimalTotem(), 1, 1, 1); //On doit récuperer les chiffres depuis le front !!!!
            amour = result.get(0);
            sante = result.get(1);
            travail = result.get(2);
        } catch (IOException ex) {
            DebugLogger.log("La prédiction n'a pas pu être générée.", ex);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmour() {
        return amour;
    }

    public void setAmour(String amour) {
        this.amour = amour;
    }

    public String getSante() {
        return sante;
    }

    public void setSante(String sante) {
        this.sante = sante;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
    
}
