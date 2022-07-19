package dal;

import bll.ConnectionManager;
import bo.Actor;
import bo.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CountryDAO implements DAO<Country>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Country object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création de l'acteur", e);
        }
    }

    public Country selectByName(String name) {
        TypedQuery<Country> query = em.createQuery("select r from Country r where r.name = :name", Country.class);
        query.setParameter("name", name);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
