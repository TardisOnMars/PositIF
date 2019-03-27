package fr.insalyon.dasi.positif.Metier.Modele;

import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-27T11:25:40")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> Prenom;
    public static volatile SingularAttribute<Employe, String> MotDePasse;
    public static volatile SingularAttribute<Employe, Boolean> Disponibilite;
    public static volatile SingularAttribute<Employe, String> AdresseMail;
    public static volatile SingularAttribute<Employe, Long> id;
    public static volatile SingularAttribute<Employe, String> Nom;
    public static volatile SingularAttribute<Employe, String> numTel;
    public static volatile ListAttribute<Employe, Medium> MediumCorres;

}