package dal;

import bll.ConnectionManager;
import bo.Actor;
import bo.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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

    public List<Movie> selectByActor(String name) {
        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m JOIN m.actors a where a.identity = :identity ", Movie.class).setParameter("identity", name);
        List<Movie> movies = query.getResultList();

        return movies;
    }


    public Movie selectByImdbId(String imdbId) {
        TypedQuery<Movie> query = em.createQuery("select m from Movie m where m.imdbId = :imdbId", Movie.class);
        query.setParameter("imdbId", imdbId);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public List<Movie> selectByYearInterval(String min, String max) {
        TypedQuery<Movie> query = em.createQuery("select m from Movie m where m.releaseYear >= :min AND m.releaseYear <= :max", Movie.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        List<Movie> movies = query.getResultList();
        return movies;
    }

    public List<Movie> selectMovieByCommonActor(String actor1, String actor2) {
        TypedQuery<Movie> query = em.createQuery("SELECT DISTINCT m FROM Movie m JOIN m.actors a WHERE " +
                "a.identity = :actor1 AND " +
                "m.id IN (SELECT m.id FROM " +
                "Movie m JOIN m.actors a WHERE " +
                "a.identity = :actor2)", Movie.class);
        query.setParameter("actor1", actor1);
        query.setParameter("actor2", actor2);

        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }

    public List<Movie> getFilmByActorAndDate(String actor, String year1, String year2) {
        TypedQuery<Movie> query = em.createQuery("SELECT DISTINCT f FROM Movie f JOIN f.actors a WHERE " +
                "a.identity = :actor AND " +
                "f.id IN (SELECT f.id FROM " +
                "Movie f JOIN f.actors a WHERE " +
                "f.releaseYear >= :year1 AND f.releaseYear <= :year2)", Movie.class);
        query.setParameter("actor", actor);
        query.setParameter("year1", year1);
        query.setParameter("year2", year2);

        return query.getResultList().size() > 0 ? query.getResultList() : null;
    }


}
