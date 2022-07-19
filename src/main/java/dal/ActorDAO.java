package dal;

import bll.ConnectionManager;
import bo.Actor;
import bo.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActorDAO implements DAO<Actor> {


    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();


    @Override
    public void create(Actor object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création de l'acteur", e);
        }

    }

    public void update(Actor object) throws DALException{
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
    }

    public Actor selectByIdentity(String identity) {
        TypedQuery<Actor> query = em.createQuery("select r from Actor r where r.identity = :identity", Actor.class);
        query.setParameter("identity", identity);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
    public Actor selectByImdbId(String id) {
        TypedQuery<Actor> query = em.createQuery("select r from Actor r where r.imdbId = :imdbId", Actor.class);
        query.setParameter("imdbId", id);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

    public List<Actor> selectCastByMovie(String movie) {
        TypedQuery<Actor> query = em.createQuery("SELECT a FROM Actor a JOIN a.movies m where m.name = :movie", Actor.class);
        query.setParameter("movie", movie);
        List<Actor> cast = query.getResultList();
        return cast;
    }

    public List<Actor> selectByCommonMovie(String movie1, String movie2) {
        TypedQuery<Actor> query = em.createQuery("SELECT DISTINCT a FROM Actor a JOIN a.movies f WHERE " +
                "f.name = :movie1 AND " +
                "a.id IN (SELECT a.id FROM " +
                "Actor a JOIN a.movies f WHERE " +
                "f.name = :movie2)", Actor.class);
        query.setParameter("movie1", movie1);
        query.setParameter("movie2", movie2);
        List<Actor> actors = query.getResultList();
        return actors;
    }



}
