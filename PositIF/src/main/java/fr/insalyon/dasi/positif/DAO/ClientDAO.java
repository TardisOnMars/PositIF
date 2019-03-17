package fr.insalyon.dasi.positif.DAO;

import fr.insalyon.dasi.positif.object.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import fr.insalyon.dasi.positif.util.DebugLogger;

public class ClientDAO {

    public ClientDAO() {

    }

    public void persistClient(Client client) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(client);
    }

    public void detruireClient(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(em.getReference(Client.class, id));
    }

    public Client findClient(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Client.class, id);
    }

    public String findClient(String adresseMail) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String jpql = "select c from Client c where c.adresseMail = :adresseMail";
        Query query = em.createQuery(jpql);
        query.setParameter("adresseMail", adresseMail);
        String resultat = ((Client)query.getSingleResult()).getMotDePasse();
        return resultat;
    }

    public List<Client> findClient() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Client c";
        Query query = em.createQuery(everyone);
        return (List<Client>) query.getResultList();
    }
}
