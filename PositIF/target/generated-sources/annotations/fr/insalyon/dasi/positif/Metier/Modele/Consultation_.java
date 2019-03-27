package fr.insalyon.dasi.positif.Metier.Modele;

import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-27T11:25:40")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Date> dateConsultation;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, String> commentaire;

}