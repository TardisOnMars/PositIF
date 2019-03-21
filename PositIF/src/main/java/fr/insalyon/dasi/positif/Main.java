package fr.insalyon.dasi.positif;

import fr.insalyon.dasi.positif.Metier.Service.Services;
import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import fr.insalyon.dasi.positif.util.DebugLogger;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        JpaUtil.init();
        Client c0 = new Client("Madame", "Villenave", "Sophie", new Date(99, 6, 9), "20 Avenue Albert Einstein", "0625235102", "sophie.villenave@insa-lyon.fr", "mdp");
        Client c1 = new Client("Monsieur", "Zhao", "Yucong", new Date(98, 6, 17), "20 Avenue Albert Einstein", "0785245684", "yucong.zhao@insa-lyon.fr", "mdp");
        Client c2 = new Client();

        Services s = new Services();
        s.init();
        s.CreerClient(c0);
        s.CreerClient(c1);
        c2 = s.ConnecterUtilisateur("yucong.zhao@insa-lyon.fr", "mdp");
        DebugLogger.log("Connexion effectuée. Nom du client connecté : " + c2.getNom() + " " + c2.getPrenom());
        List<Medium> mediums = s.recupererMediums();
        for (Medium m : mediums) {
            DebugLogger.log(m.getNom());
        }
        Consultation co1 = s.creerConsultation(c2, mediums.get(1));
        if (co1 != null) {
            s.commencerConsultation(co1);
            s.finirConsultation(co1, "C'était cool !");
        }

        JpaUtil.destroy();
    }

}
