package dal;

import bll.ConnectionManager;
import bo.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PaysDAO implements DAO<Country>{

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

    @Override
    public Country selectById(long id) throws DALException {
        return null;
    }
}
