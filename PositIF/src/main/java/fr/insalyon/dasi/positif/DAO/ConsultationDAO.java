package fr.insalyon.dasi.positif.DAO;

import fr.insalyon.dasi.positif.Metier.Modele.Client;
import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ConsultationDAO {
    
    public ConsultationDAO() {
    }
    
    public void persistConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(consultation);
    }
    
    public Consultation mergeConsultation(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.merge(consultation);
    }
    
    public void detruireConsultation(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(em.getReference(Consultation.class, id));
    }

    public Consultation findConsultation(Long id) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Consultation.class, id);
    }
    
    public List<Consultation> findConsultation() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Consultation c";
        Query query = em.createQuery(everyone);
        return (List<Consultation>) query.getResultList();
    }
    /*
    public List<Consultation> findConsultation(Client cl) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Consultation c where c.Client=cl";
        Query query = em.createQuery(everyone);
        return (List<Consultation>) query.getResultList();
    }
    
    public List<Consultation> findConsultation(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Consultation c where c.Employe=e";
        Query query = em.createQuery(everyone);
        return (List<Consultation>) query.getResultList();
    }
*/
    
}
