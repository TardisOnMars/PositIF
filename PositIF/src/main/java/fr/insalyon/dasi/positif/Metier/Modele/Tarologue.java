package fr.insalyon.dasi.positif.Metier.Modele;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Tarologue extends Medium implements Serializable{

    public Tarologue() {
    }
    
    public Tarologue(String nom, String description){
        super(nom, "Tarologue", description);
    }
    
}
