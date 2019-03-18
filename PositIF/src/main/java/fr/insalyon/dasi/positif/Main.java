package fr.insalyon.dasi.positif;
import fr.insalyon.dasi.positif.Metier.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import java.util.Date;

public class Main {

    public static void main(String args[]) {
        JpaUtil.init();
        Client c0 = new Client("Madame", "Villenave", "Sophie", new Date(99,6,9),"20 Avenue Albert Einstein","0625235102","sophie.villenave@insa-lyon.fr", "mdp");
        Client c1 = new Client("Monsieur", "Zhao", "Yucong", new Date(98,6,17),"20 Avenue Albert Einstein","0785245684","yucong.zhao@insa-lyon.fr", "mdp");
        Services s = new Services();
        s.CreerClient(c1);
        s.ConnecterUtilisateur("yucong.zhao@insa-lyon.fr", "mdp");
        JpaUtil.destroy();
    }

}
