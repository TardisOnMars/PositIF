package fr.insalyon.dasi.positif.Metier.Service;

import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.DAO.ClientDAO;
import fr.insalyon.dasi.positif.DAO.ConsultationDAO;
import fr.insalyon.dasi.positif.DAO.EmployeDAO;
import fr.insalyon.dasi.positif.DAO.MediumDAO;
import fr.insalyon.dasi.positif.Metier.Modele.Astrologue;
import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import fr.insalyon.dasi.positif.Metier.Modele.Tarologue;
import fr.insalyon.dasi.positif.Metier.Modele.Voyant;
import fr.insalyon.dasi.positif.util.DebugLogger;
import fr.insalyon.dasi.positif.util.Message;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.RollbackException;

public class Services {

    public Services() {

    }

    public void init() {
        Medium m1 = new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides !");
        Medium m2 = new Astrologue("Serena", "Serena vous révèlera votre avenir pour éclairer votre passé", "ENS-Astro", "2006");
        Medium m3 = new Voyant("Pr Maxwell", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de Café");
        Employe e1 = new Employe("Parker", "Peter", new LinkedList<>(), "mdp", "0611223344", "peter.parker@spidey.com", true);
        Employe e2 = new Employe("Gourmand", "Jamy", new LinkedList<>(), "mdp", "0612234567", "jamy.gourmand@cpassorcier.com", true);
        Employe e3 = new Employe("Tennant", "David", new LinkedList<>(), "mdp", "0699333231", "david.tennant@doctorwho.com", true);

        e1.getMediumCorres().add(m1);
        e2.getMediumCorres().add(m2);
        e2.getMediumCorres().add(m3);
        e3.getMediumCorres().add(m2);
        e3.getMediumCorres().add(m3);

        EmployeDAO eDAO = new EmployeDAO();
        MediumDAO mDAO = new MediumDAO();

        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            mDAO.persistMedium(m1);
            mDAO.persistMedium(m2);
            mDAO.persistMedium(m3);
            eDAO.persistEmploye(e1);
            eDAO.persistEmploye(e2);
            eDAO.persistEmploye(e3);
            JpaUtil.validerTransaction();
            DebugLogger.log("Initialisation réussie !");
        } catch (RollbackException ex) {
            DebugLogger.log("Initialisation échouée !");
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    public void CreerClient(Client client) {
        ClientDAO cDao = new ClientDAO();
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            cDao.persistClient(client);
            JpaUtil.validerTransaction();
            Message.envoyerMail("PositIF", client.getAdresseMail(), "Création de compte réussie", "Bienvenue chez PositIF ! \nVotre numéro client est : " + client.getId());
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

    public Client ConnecterUtilisateur(String adresseMail, String motDePasse) {
        ClientDAO cDao = new ClientDAO();
        Client c = new Client();
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            if ((cDao.findClient(adresseMail).getMotDePasse()).equals(motDePasse)) {
                c = cDao.findClient(adresseMail);
            }
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("Ce compte client n'existe pas");
            c = null;
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return c;
    }

    public List<Medium> recupererMediums() {
        List<Medium> listeMedium;
        MediumDAO mDao = new MediumDAO();
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            listeMedium = mDao.findMedium();
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            listeMedium = null;
            DebugLogger.log("La recherche des mediums a échoué");
        } finally {
            JpaUtil.fermerEntityManager();
        }
        return listeMedium;
    }

    public Consultation creerConsultation(Client c, Medium m) {

        Employe e = new Employe();
        EmployeDAO eDao = new EmployeDAO();
        Consultation consult;
        ConsultationDAO coDao = new ConsultationDAO();
        EmployeDAO eDAO = new EmployeDAO();
        List<Employe> lEmp = new LinkedList<>();
        List<Employe> lEmpDispo = new LinkedList<>();
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            lEmp = eDAO.findEmploye();
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("La recherche des employés a échoué");
        } finally {
            JpaUtil.fermerEntityManager();
        }
        for (Employe emp : lEmp) {
            if (emp.getDisponibilite() == true) {
                for (Medium med : emp.getMediumCorres()) {
                    if (med.getNom().equals(m.getNom())) {
                        lEmpDispo.add(emp);
                    }
                }
            }
        }
        int min = -1;
        for (Employe empDispo : lEmpDispo) {
            int nbConsult = empDispo.getLConsult().size();
            if (min == -1 || nbConsult <= min) {
                min = nbConsult;
                e = empDispo;
            }
        }
        if (min == -1) {
            consult = null;
        } else {
            Message.envoyerNotification(e.getNumTel(), "Voyance demandée le " + new Date() + " pour " + c.getNom() + " " + c.getPrenom());
            e.setDisponibilite(false);
            consult = new Consultation(new Date(), c, e, m);
            try {
                JpaUtil.creerEntityManager();
                JpaUtil.ouvrirTransaction();
                coDao.persistConsultation(consult);
                e = eDao.mergeEmploye(e);
                JpaUtil.validerTransaction();
            } catch (RollbackException ex) {
                consult = null;
                DebugLogger.log("La création de consultation a échoué");
            } finally {
                JpaUtil.fermerEntityManager();
            }
        }
        return consult;
    }

    public void commencerConsultation(Consultation consult) {
        Client c = consult.getClient();
        Employe e = consult.getEmploye();
        EmployeDAO eDao = new EmployeDAO();
        Medium m = consult.getMedium();
        e.getLConsult().add(consult);
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            e = eDao.mergeEmploye(e);
            JpaUtil.validerTransaction();
            Message.envoyerNotification(c.getNumTel(), "Votre demande de voyance du " + consult.getDateConsultation() + " a bien été enregistrée."
                    + "\nVous pouvez dès à présent me contacter au " + e.getNumTel() + ". A tout de suite ! \nPosit'ifement vôtre, " + m.getNom());
        } catch (RollbackException ex) {
            DebugLogger.log("La consultation n'a pas pu commencer.");
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }

    public void finirConsultation(Consultation consult, String commentaire) {
        Employe e = consult.getEmploye();
        EmployeDAO eDao = new EmployeDAO();
        ConsultationDAO coDao = new ConsultationDAO();
        e.setDisponibilite(true);
        consult.setCommentaire(commentaire);
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            coDao.mergeConsultation(consult);
            e = eDao.mergeEmploye(e);
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("La consultation n'a pas pu être terminée");
        } finally {
            JpaUtil.fermerEntityManager();
        }
    }
}
