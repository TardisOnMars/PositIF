package fr.insalyon.dasi.positif.DAO;

import fr.insalyon.dasi.positif.Metier.Modele.Consultation;
import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class EmployeDAO {
    
    public EmployeDAO(){
        
    }
    
    public void persistEmploye(Employe employe){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(employe);
    }
    
    public Employe mergeEmploye(Employe employe){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.merge(employe);
    }
    
    public void detruireEmploye(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.remove(em.getReference(Employe.class, id));
    }
    
    public Employe findEmploye(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Employe.class, id);
    }
    
    public Employe findEmploye(String adresseMail){
        Employe resultat;
        EntityManager em = JpaUtil.obtenirEntityManager();
        String jpql = "select e from Employe e where e.adresseMail = :adresseMail";
        Query query = em.createQuery(jpql);
        query.setParameter("adresseMail", adresseMail);
        try{
            resultat = (Employe) query.getSingleResult();
        }catch(NoResultException e){
            resultat = null;
        }
        return resultat;
    }
    
    public List<Employe> findEmploye(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Employe c";
        Query query = em.createQuery(everyone);
        return (List<Employe>) query.getResultList();
    }
    
    public List<Consultation> findConsultationOfEmploye(Employe e){
        EntityManager em = JpaUtil.obtenirEntityManager();
        String jpql = "select c from Consultation c where c.employe = :e";
        Query query = em.createQuery(jpql);
        query.setParameter("e", e);
        return (List<Consultation>) query.getResultList();
    }
}
