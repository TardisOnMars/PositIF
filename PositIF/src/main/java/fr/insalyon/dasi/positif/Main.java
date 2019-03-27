package fr.insalyon.dasi.positif;

import fr.insalyon.dasi.positif.Metier.Service.Services;
import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.DAO.JpaUtil;
import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import fr.insalyon.dasi.positif.util.DebugLogger;
import fr.insalyon.dasi.positif.util.Saisie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
/*
    public static void main(String args[]) {
        JpaUtil.init();
        Client c0 = new Client("Madame", "Villenave", "Sophie", new Date(99, 6, 9), "20 Avenue Albert Einstein", "0625235102", "sophie.villenave@insa-lyon.fr", "mdp");
        Client c1 = new Client("Monsieur", "Zhao", "Yucong", new Date(98, 6, 17), "20 Avenue Albert Einstein", "0785245684", "yucong.zhao@insa-lyon.fr", "mdp");
        Client c2 = new Client();
        Services s = new Services();
        s.init();
        //TestCreationClient
        s.CreerClient(c0);
        s.CreerClient(c1);

        //TestConnexionClient
        c2 = s.connecterClient("sophie.villenave@insa-lyon.fr", "mdp");
        DebugLogger.log("Connexion effectuée. Nom du client connecté : " + c2.getNom() + " " + c2.getPrenom());

        //TestAffichageMediumsPourSelection
        List<Medium> mediums = s.recupererMediums();
        for (Medium m : mediums) {
            DebugLogger.log(m.getNom());
        }

        //TestCreationConsultation
        Consultation co1 = s.creerConsultation(c2, mediums.get(1));
        if (co1 != null) {
            //TestCommencerConsultation
            s.commencerConsultation(co1);
            //TestGenerationPredictions
            List<String> predictions = s.genererPredictions(co1, 1, 2, 3);
            DebugLogger.log(predictions.get(0) + "\n" + predictions.get(1) + "\n" + predictions.get(2));
            //TestFinirConsultation
            s.finirConsultation(co1, "Le client est charmé !");
        }

        //TestGenererHistorique
        List<Consultation> histoC2 = s.genererHistoriqueClient(c2);
        for (Consultation c : histoC2) {
            DebugLogger.log(c.getClient().getNom());
        }

        //TestPopulariteMedium
        Map<Medium, Integer> popMed = s.PopulatiteMedium();

        System.out.println("Parcours de l'objet HashMap : ");
        Set<Entry<Medium, Integer>> setHm = popMed.entrySet();
        Iterator<Entry<Medium, Integer>> it = setHm.iterator();
        while (it.hasNext()) {
            Entry<Medium, Integer> e = it.next();
            System.out.println(e.getKey().getNom() + " : " + e.getValue());
        }

        //TestPopulariteEmploye
        Map<Employe, Integer> popEmp = s.RepartitionEmploye();

        System.out.println("Parcours de l'objet HashMap : ");
        Set<Entry<Employe, Integer>> setHmEmp = popEmp.entrySet();
        Iterator<Entry<Employe, Integer>> itEmp = setHmEmp.iterator();
        while (itEmp.hasNext()) {
            Entry<Employe, Integer> e = itEmp.next();
            System.out.println(e.getKey().getNom() + " : " + e.getValue());
        }

        JpaUtil.destroy();
    }
*/
    public static void main(String args[]) {
        JpaUtil.init();
        Services s = new Services();
        s.init();

        System.out.println("Postit\'IF Backend - Auteurs : Villenave Sophie et Zhao Yucong.");
        String input = "";
        String description = "Quel test voulez vous effectuer ? (Tapez le code entre parenthèse)\n"
                + "Inscription Client (ic)\n"
                + "Login Client (lc)\n"
                + "Consulter l'historique d'un client (hc)\n"
                + "Login Employe (le)\n"
                + "Recupérer la liste des médiums (lm)\n"
                + "Simuler une consultation (cc)\n"
                + "Afficher le tableau de bord (atb)\n"
                + "Quitter le programme (quit)\n"
                + "\n=====\n";
        while (!input.equals("quit")) {
            input = Saisie.lireChaine(description);
            switch (input) {

                case "ic":
                    inscriptionClient();
                    break;

                case "lc":
                    loginClient();
                    break;

                case "hc":
                    historiqueClient();
                    break;

                case "le":
                    loginEmploye();
                    break;

                case "lm":
                    listeMediums();
                    break;

                case "cc":
                    testConsultation();
                    break;

                case "atb":
                    affTabBord();
                    break;

                case "quit":
                    System.out.println("Fin du programme.");
                    break;

                default:
                    System.out.println("Commande non reconnue.");
                    break;
            }
        }
    }

    public static void inscriptionClient() {
        Services s = new Services();
        String infos[] = new String[8];
        String prompt[] = {
            "Indiquez votre civilité : ",
            "Saisissez votre nom : ",
            "Saisissez votre prénom : ",
            "Saisissez votre date de naissance (jj/MM/AAAA) : ",
            "Saisissez votre adresse : ",
            "Saisissez votre numéro de téléphone : ",
            "Saisissez votre e-mail : ",
            "Saisissez votre mot de passe : "
        };

        System.out.println("Veuillez remplir les champs pour vous inscrire : ");
        for (int i = 0; i < 8; i++) {
            infos[i] = Saisie.lireChaine(prompt[i]);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Client c = new Client(infos[0], infos[1], infos[2], sdf.parse(infos[3]), infos[4],
                    infos[5], infos[6], infos[7]);
            s.CreerClient(c);
        } catch (ParseException e) {
            DebugLogger.log("La transcription de la date n'a pas fonctionnée. Inscription annulée");
        }
    }

    public static Client loginClient() {
        Services s = new Services();
        String email;
        String mdp;
        System.out.println("Page de connexion Client\n"
                + "Veuillez entrer votre adresse e-mail et votre mot de passe :\n");
        email = Saisie.lireChaine("E-mail : ");
        mdp = Saisie.lireChaine("Mot de passe : ");

        Client c = s.connecterClient(email, mdp);
        if (c == null) {
            System.out.println("Identifiants incorrects.\n");
        } else {
            System.out.println("Bonjour " + c.getCivilite() + " "
                    + c.getNom() + ", vous vous êtes authentifié avec succès.\n");
            System.out.println("Voici votre profil Astrologique : \n"
                    + c.getCivilite() + " " + c.getNom() + " " + c.getPrenom() + ":\n"
                    + "Signe astrologique : " + c.getSigneAstro() + "\n"
                    + "Animal Totem : " + c.getAnimalTotem() + "\n"
                    + "Couleur porte-bonheur : " + c.getColBonheur() + "\n");
        }
        return c;
    }

    public static void historiqueClient() {
        Client c = loginClient();
        Services s = new Services();
        List<Consultation> histoC = s.genererHistoriqueClient(c);
        for (Consultation cslt : histoC) {
            System.out.println("Consultation avec : " + cslt.getMedium().getNom() + " le " + cslt.getDateConsultation());
        }
    }

    public static Employe loginEmploye() {
        Services s = new Services();
        String email;
        String mdp;
        System.out.println("Page de connexion Employe\n"
                + "Veuillez entrer votre adresse e-mail et votre mot de passe :\n");
        email = Saisie.lireChaine("E-mail : ");
        mdp = Saisie.lireChaine("Mot de passe : ");

        Employe e = s.connecterEmploye(email, mdp);
        if (e == null) {
            System.out.println("Identifiants incorrects.\n");
        } else {
            System.out.println("Bonjour " + e.getPrenom() + " "
                    + e.getNom() + ", vous vous êtes authentifié avec succès.\n");
        }
        return e;
    }

    public static void listeMediums() {
        Services s = new Services();
        List<Medium> mediums = s.recupererMediums();
        for (Medium m : mediums) {
            System.out.println("Medium : " + m.getNom());
        }
    }

    public static void testConsultation() {
        Services s = new Services();
        Client c = loginClient();
        List<Medium> mediums = s.recupererMediums();
        Consultation co1 = s.creerConsultation(c, mediums.get(1));
        if (co1 != null) {
            //TestCommencerConsultation
            s.commencerConsultation(co1);
            //TestGenerationPredictions
            Integer amour = Saisie.lireInteger("Saisissez le score d'amour (1 à 4) : ");
            Integer travail = Saisie.lireInteger("Saisissez le score de travail (1 à 4) : ");
            Integer sante = Saisie.lireInteger("Saisissez le score de sante (1 à 4) : ");
            List<String> predictions = s.genererPredictions(co1, amour, travail, sante);
            System.out.println("Amour : " + predictions.get(0) + "\nTravail : " + predictions.get(1) + "\nSante : " + predictions.get(2));
            //TestFinirConsultation
            s.finirConsultation(co1, "Le client est charmé !");
        } else {
            System.out.println("La consultation n'a pas pu être créée car le médium n'est pas disponible");
        }
    }
    
    public static void affTabBord(){
        Services s = new Services();

        Map<Medium, Integer> popMed = s.populariteMedium();

        System.out.println("Parcours de la map de medium : ");
        Set<Entry<Medium, Integer>> setHm = popMed.entrySet();
        Iterator<Entry<Medium, Integer>> it = setHm.iterator();
        while (it.hasNext()) {
            Entry<Medium, Integer> e = it.next();
            System.out.println(e.getKey().getNom() + " : " + e.getValue());
        }

        Map<Employe, Integer> popEmp = s.repartitionEmploye();

        System.out.println("Parcours de la map d'employe : ");
        Set<Entry<Employe, Integer>> setHmEmp = popEmp.entrySet();
        Iterator<Entry<Employe, Integer>> itEmp = setHmEmp.iterator();
        while (itEmp.hasNext()) {
            Entry<Employe, Integer> e = itEmp.next();
            System.out.println(e.getKey().getNom() + " : " + e.getValue());
        }
    }

}
