package fr.insalyon.dasi.positif.DAO;

import fr.insalyon.dasi.positif.Metier.Modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MediumDAO {

    public MediumDAO() {
    }
    
    public void persistMedium(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(medium);
    }

    public void detruireMedium(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(em.getReference(Medium.class, id));
    }

    public Medium findMedium(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Medium.class, id);
    }
    
    public List<Medium> findMedium() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select m from Medium m";
        Query query = em.createQuery(everyone);
        return (List<Medium>) query.getResultList();
    }
}
