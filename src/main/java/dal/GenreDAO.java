package dal;

import bll.ConnectionManager;
import bo.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class GenreDAO implements DAO<Genre>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Genre object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du film", e);
        }
    }

    @Override
    public Genre selectById(long id) throws DALException {
        return null;
    }
}
