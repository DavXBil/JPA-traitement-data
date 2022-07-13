package dal;

import bll.ConnectionManager;
import bo.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    @Override
    public void update(Actor object) throws DALException {

    }

    @Override
    public void delete(Actor object) throws DALException {

    }

    @Override
    public List<Actor> selectAll() throws DALException {
        return null;
    }

    @Override
    public Actor selectById(long id) throws DALException {
        return null;
    }

}
