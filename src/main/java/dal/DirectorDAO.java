package dal;

import bll.ConnectionManager;
import bo.Actor;
import bo.Director;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DirectorDAO implements DAO<Director>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Director object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du réalisateur", e);
        }
    }

    public Director selectByIdentity(String identity) {
        TypedQuery<Director> query = em.createQuery("select d from Director d where d.identity = :identity", Director.class);
        query.setParameter("identity", identity);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
