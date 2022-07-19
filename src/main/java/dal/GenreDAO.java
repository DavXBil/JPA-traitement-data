package dal;

import bll.ConnectionManager;
import bo.Actor;
import bo.Genre;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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


    public Genre selectByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select r from Genre r where r.name = :name", Genre.class);
        query.setParameter("name", name);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
