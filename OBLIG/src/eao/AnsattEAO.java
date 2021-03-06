package eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Ansatt;
import entities.Prosjekt;

public class AnsattEAO {
	
    private EntityManagerFactory emf;
	
    public AnsattEAO() 
    {
        emf = Persistence.createEntityManagerFactory("AnsattPersistenceUnit");
    }
	
	public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();
        //kommenterte ut Stian, funker
       //Ansatt a = null;

        try {
         return   em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
      //kommenterte ut Stian, funker
       //return a;
    }
	
	
	public Ansatt finnAnsattMedBrukerNavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        Ansatt a = null;

        try {
        	String queryString ="SELECT a FROM Ansatt a WHERE a.brukernavn =:brukernavn";
        	TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
        	query.setParameter("brukernavn", brukernavn);
            a = query.getSingleResult();
        } finally {
            em.close();
        }
        return a;
    }
	

	public void registrerProsjektDeltakelse(Ansatt a, Prosjekt p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			a = em.merge(a);
			p = em.merge(p);
			//legg til prosjekt
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.clear();
		}
	}

	
	public List<Ansatt> hentAlleAnsatte() {
        EntityManager em = emf.createEntityManager();
        List<Ansatt> ansatte = null;

        try {
			TypedQuery<Ansatt> query = em.createNamedQuery("hentAlleAnsatte", Ansatt.class);
			ansatte = query.getResultList();
        } finally {
            em.close();
        }
        return ansatte;
    }
	
	public void opprettAnsatt(Ansatt a) {
		
        EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(a);
			tx.commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public void oppdaterAnsatt(Ansatt a) {

	     EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.merge(a);
			em.getTransaction().commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	
}
