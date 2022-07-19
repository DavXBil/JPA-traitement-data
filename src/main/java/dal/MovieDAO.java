package dal;

import bll.ConnectionManager;
import bo.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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


    public Movie selectByImdbId(String imdbId) {
        TypedQuery<Movie> query = em.createQuery("select m from Movie m where m.imdbId = :imdbId", Movie.class);
        query.setParameter("imdbId", imdbId);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
