package fr.insalyon.dasi.positif.DAO;

import fr.insalyon.dasi.positif.Metier.Modele.Employe;
import java.util.List;
import javax.persistence.EntityManager;
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
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Employe.class, adresseMail);
    }
    
    public List<Employe> findEmploye(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        String everyone = "select c from Employe c";
        Query query = em.createQuery(everyone);
        return (List<Employe>) query.getResultList();
    }
}
