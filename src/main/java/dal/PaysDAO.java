package dal;

import bll.ConnectionManager;
import bo.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
    public void update(Country object) throws DALException {

    }

    @Override
    public void delete(Country object) throws DALException {

    }

    @Override
    public List<Country> selectAll() throws DALException {
        return null;
    }

    @Override
    public Country selectById(long id) throws DALException {
        return null;
    }
}
