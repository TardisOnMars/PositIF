package fr.insalyon.dasi.positif;
import fr.insalyon.dasi.positif.Metier.Service.Services;
import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import fr.insalyon.dasi.positif.Metier.Modele.Prediction;
import fr.insalyon.dasi.positif.Metier.Modele.Tarologue;
import fr.insalyon.dasi.positif.util.DebugLogger;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        JpaUtil.init();
        Client c0 = new Client("Madame", "Villenave", "Sophie", new Date(99,6,9),"20 Avenue Albert Einstein","0625235102","sophie.villenave@insa-lyon.fr", "mdp");
        Client c1 = new Client("Monsieur", "Zhao", "Yucong", new Date(98,6,17),"20 Avenue Albert Einstein","0785245684","yucong.zhao@insa-lyon.fr", "mdp");
        Employe e = new Employe("Jamy", "Gourmand", null, "mdp", "@mail", true, "Type");
        Medium m = new Tarologue("Myste", "Herieux");
        Consultation co = new Consultation(new Date(119, 3, 19),c1, e, m);
        Prediction p = new Prediction(co);
        DebugLogger.log(p.getAmour());
        Services s = new Services();
        s.CreerClient(c1);
        s.ConnecterUtilisateur("yucong.zhao@insa-lyon.fr", "mdp");
        JpaUtil.destroy();
    }

}
