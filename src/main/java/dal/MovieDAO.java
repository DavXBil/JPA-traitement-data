package dal;

import bll.ConnectionManager;
import bo.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MovieDAO implements DAO<Movie>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();



    @Override
    public void create(Movie object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du film", e);
        }
    }

    @Override
    public void update(Movie object) throws DALException {

    }

    @Override
    public void delete(Movie object) throws DALException {

    }

    @Override
    public List<Movie> selectAll() throws DALException {
        return null;
    }

    @Override
    public Movie selectById(long id) throws DALException {
        return null;
    }
}
