package dal;

import bll.ConnectionManager;
import bo.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

    @Override
    public Actor selectById(long id) throws DALException {
        return null;
    }

    public Actor selectByIdentity(String identity) {
        TypedQuery<Actor> query = em.createQuery("select r from Actor r where r.identity = :identity", Actor.class);
        query.setParameter("identity", identity);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
