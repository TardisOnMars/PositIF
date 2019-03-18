package fr.insalyon.dasi.positif;

import fr.insalyon.dasi.positif.Metier.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.DAO.ClientDAO;
import fr.insalyon.dasi.positif.util.DebugLogger;
import fr.insalyon.dasi.positif.util.Message;

public class Services {

    public Services() {

    }

    public void CreerClient(Client client) {
        ClientDAO cDao = new ClientDAO();

        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            cDao.persistClient(client);
            JpaUtil.validerTransaction();
            Message.envoyerMail("PositIF", client.getAdresseMail(), "Création Réussie", "Bravo!!!");

        } catch (Exception ex) {
            Message.envoyerMail("PositIF", client.getAdresseMail(), "Création Echouée", "Pas Bravo!!!");
        } finally {
            JpaUtil.fermerEntityManager();
        }

    }

    public Client RecupererProfil(Long id) {
        ClientDAO cDao = new ClientDAO();
        Client c;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        c = cDao.findClient(id);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        return c;
    }

    public boolean ConnecterUtilisateur(String adresseMail, String motDePasse) {
        boolean connexion = false;
        ClientDAO cDao = new ClientDAO();
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            DebugLogger.log(cDao.findClient(adresseMail));
            if ((cDao.findClient(adresseMail)).equals(motDePasse)) {
                connexion = true;
            }
        } catch (Exception ex) {}
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return connexion;
    }
    
}
