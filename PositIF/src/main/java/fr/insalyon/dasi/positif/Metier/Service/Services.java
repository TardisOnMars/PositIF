package fr.insalyon.dasi.positif.Metier.Service;

import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.DAO.ClientDAO;
import fr.insalyon.dasi.positif.DAO.EmployeDAO;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import fr.insalyon.dasi.positif.util.DebugLogger;
import fr.insalyon.dasi.positif.util.Message;
import javax.persistence.RollbackException;

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
            Message.envoyerMail("PositIF", client.getAdresseMail(), "Création de compte réussie", "Bienvenue chez PositIF !");
        } catch (RollbackException ex) {
            Message.envoyerMail("PositIF", client.getAdresseMail(), "Création de compte échouée", "Veuillez réessayer ultérieurement.");
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    public Client RecupererClient(Long id) {
        ClientDAO cDao = new ClientDAO();
        Client c;
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            c = cDao.findClient(id);
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            c = null;
            DebugLogger.log("La recherche du client a échoué");
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return c;
    }
    
    public Employe RecupererEmploye(Long id) {
        EmployeDAO eDao = new EmployeDAO();
        Employe e;
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            e = eDao.findEmploye(id);
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            e = null;
            DebugLogger.log("La recherche du client a échoué");
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return e;
    }

    public boolean ConnecterUtilisateur(String adresseMail, String motDePasse) {
        boolean connexion = false;
        ClientDAO cDao = new ClientDAO();
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            if ((cDao.findClient(adresseMail)).equals(motDePasse)) {
                connexion = true;
            }
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("Ce compte client n'existe pas");
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return connexion;
    }
    
    
}
